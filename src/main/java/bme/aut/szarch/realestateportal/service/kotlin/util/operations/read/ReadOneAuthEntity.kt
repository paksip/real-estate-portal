package bme.aut.szarch.realestateportal.service.kotlin.util.operations.read

import bme.aut.szarch.realestateportal.domain.User
import bme.aut.szarch.realestateportal.domain.kotlin.AbstractUserRelatedEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.checkAuthorization
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun <EntityClass : AbstractUserRelatedEntity, DTOClass : Any> executeReadWithAuthorization(
    getUserCall: () -> User?,
    getEntityCall: (Long) -> EntityClass?,
    validationCall: ((EntityClass) -> Unit)? = null,
    mappingCall: (EntityClass) -> DTOClass,
    onSuccess: (DTOClass) -> DataTransferResult<DTOClass>
): ResponseEntity<DTOClass> {
    return try {
        val user = getUserCall()
        check(user != null) { "The user not Authenticated!" }

        val authEntity = getEntityCall(user.id)
        check(authEntity != null) { "The related authenticated entity does not exist" }
        checkAuthorization(authEntity.user.id, user.id)

        validationCall?.invoke(authEntity)

        val dto = mappingCall(authEntity)

        onSuccess(dto).toResponseEntity()
    } catch (e: Exception) {
        DataTransferResult.Error(HttpStatus.BAD_REQUEST, e.message).toResponseEntity()
    }
}
