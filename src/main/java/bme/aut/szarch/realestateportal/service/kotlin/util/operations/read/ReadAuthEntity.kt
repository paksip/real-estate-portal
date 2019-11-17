package bme.aut.szarch.realestateportal.service.kotlin.util.operations.read

import bme.aut.szarch.realestateportal.domain.User
import bme.aut.szarch.realestateportal.domain.kotlin.AbstractUserRelatedEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.validation.checkAuthentication
import bme.aut.szarch.realestateportal.service.kotlin.util.validation.checkAuthorization
import bme.aut.szarch.realestateportal.service.kotlin.util.validation.checkEntityNotFound
import org.springframework.http.ResponseEntity

inline fun <reified EntityClass : AbstractUserRelatedEntity, DTOClass : Any> executeReadWithAuthorization(
    getUserCall: () -> User?,
    getEntityCall: (Long) -> EntityClass?,
    noinline validationCall: ((EntityClass) -> Unit)? = null,
    mappingCall: (EntityClass) -> DTOClass,
    onSuccess: (DTOClass) -> DataTransferResult<DTOClass>
): ResponseEntity<DTOClass> {
    val user = getUserCall().checkAuthentication()

    val authEntity = getEntityCall(user.id).checkEntityNotFound()
    checkAuthorization(authEntity.user.id, user.id)

    validationCall?.invoke(authEntity)

    val dto = mappingCall(authEntity)

    return onSuccess(dto).toResponseEntity()
}