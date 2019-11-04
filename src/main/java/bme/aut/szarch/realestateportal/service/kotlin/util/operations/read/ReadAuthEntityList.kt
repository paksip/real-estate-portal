package bme.aut.szarch.realestateportal.service.kotlin.util.operations.read

import bme.aut.szarch.realestateportal.domain.User
import bme.aut.szarch.realestateportal.domain.kotlin.AbstractUserRelatedEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.validation.checkAuthentication
import bme.aut.szarch.realestateportal.service.kotlin.util.validation.checkAuthorization
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity

fun <EntityClass : Page<AuthEntityClass>, AuthEntityClass : AbstractUserRelatedEntity, DTOClass : Any> executeReadListWithAuthorization(
    getUserCall: () -> User?,
    getEntityListCall: (Long) -> EntityClass,
    validationCall: ((EntityClass) -> Unit)? = null,
    mappingCall: (EntityClass) -> DTOClass,
    onSuccess: (DTOClass) -> DataTransferResult<DTOClass>
): ResponseEntity<DTOClass> {
    val user = getUserCall().checkAuthentication()

    val callResult = getEntityListCall(user.id)

    if (callResult.content.isNotEmpty()) {
        val authEntity = callResult.content.first()
        checkAuthorization(authEntity.user.id, user.id)
    }

    validationCall?.invoke(callResult)

    val dto = mappingCall(callResult)

    return onSuccess(dto).toResponseEntity()
}
