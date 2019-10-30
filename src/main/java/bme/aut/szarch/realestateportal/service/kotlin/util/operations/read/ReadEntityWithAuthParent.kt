package bme.aut.szarch.realestateportal.service.kotlin.util.operations.create

import bme.aut.szarch.realestateportal.domain.User
import bme.aut.szarch.realestateportal.domain.kotlin.AbstractUserRelatedEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.checkAuthorization
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


fun <EntityClass : Any, AuthParentEntityClass : AbstractUserRelatedEntity, DTOClass : Any> executeReadWithAuthParent(
    getUserCall: () -> User?,
    getUserRelatedParentEntityCalls: List<() -> AuthParentEntityClass?>,
    getTargetEntity: () -> EntityClass?,
    validationCall: ((EntityClass) -> Unit)? = null,
    mappingCall: (EntityClass) -> DTOClass,
    onSuccess: (DTOClass) -> DataTransferResult<DTOClass>
): ResponseEntity<DTOClass> {
    return try {
        val user = getUserCall()
        check(user != null) { "The user not Authenticated!" }
        getUserRelatedParentEntityCalls.forEach { getUserRelatedParentEntityCall ->
            val authParentEntity = getUserRelatedParentEntityCall()
            check(authParentEntity != null) { "The related authenticated entity does not exist" }
            checkAuthorization(authParentEntity.user.id, user.id)
        }

        val callResult = getTargetEntity()
        check(callResult != null) { "Does not exists any recourse with id" }


        validationCall?.invoke(callResult)

        val dto = mappingCall(callResult)

        onSuccess(dto).toResponseEntity()
    } catch (e: Exception) {
        DataTransferResult.Error(HttpStatus.BAD_REQUEST, e.message).toResponseEntity()
    }
}
