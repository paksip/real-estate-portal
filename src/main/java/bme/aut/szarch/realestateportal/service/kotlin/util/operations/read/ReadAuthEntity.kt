package bme.aut.szarch.realestateportal.service.kotlin.util.operations.create

import bme.aut.szarch.realestateportal.domain.User
import bme.aut.szarch.realestateportal.domain.kotlin.AbstractUserRelatedEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.checkAuthorization
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun <EntityClass : Page<AuthEntityClass>, AuthEntityClass : AbstractUserRelatedEntity, DTOClass : Any> executeReadListWithAuthorization(
    getUserCall: () -> User?,
    getEntityListCall: (Long) -> EntityClass?,
    validationCall: ((EntityClass) -> Unit)? = null,
    mappingCall: (EntityClass) -> DTOClass,
    onSuccess: (DTOClass) -> DataTransferResult<DTOClass>
): ResponseEntity<DTOClass> {
    return try {
        val user = getUserCall()
        check(user != null) { "The user not Authenticated!" }

        val callResult = getEntityListCall(user.id)
        check(callResult != null) { "The related authenticated entity does not exist" }

        if (callResult.content.isNotEmpty()) {
            val authEntity = callResult.content.first()
            checkAuthorization(authEntity.user.id, user.id)
        }

        validationCall?.invoke(callResult)

        val dto = mappingCall(callResult)

        onSuccess(dto).toResponseEntity()
    } catch (e: Exception) {
        DataTransferResult.Error(HttpStatus.BAD_REQUEST, e.message).toResponseEntity()
    }
}
