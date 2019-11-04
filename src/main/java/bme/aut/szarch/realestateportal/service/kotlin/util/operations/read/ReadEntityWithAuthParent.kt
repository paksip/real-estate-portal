package bme.aut.szarch.realestateportal.service.kotlin.util.operations.read

import bme.aut.szarch.realestateportal.domain.User
import bme.aut.szarch.realestateportal.domain.kotlin.AbstractUserRelatedEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.validation.checkAuthentication
import bme.aut.szarch.realestateportal.service.kotlin.util.validation.checkAuthorization
import bme.aut.szarch.realestateportal.service.kotlin.util.validation.checkEntityNotFound
import org.springframework.http.ResponseEntity


inline fun <reified EntityClass : Any, reified AuthParentEntityClass : AbstractUserRelatedEntity, DTOClass : Any> executeReadWithAuthParent(
    getUserCall: () -> User?,
    getUserRelatedParentEntityCalls: List<() -> AuthParentEntityClass?>,
    getTargetEntity: () -> EntityClass?,
    noinline validationCall: ((EntityClass) -> Unit)? = null,
    mappingCall: (EntityClass) -> DTOClass,
    onSuccess: (DTOClass) -> DataTransferResult<DTOClass>
): ResponseEntity<DTOClass> {
    val user = getUserCall().checkAuthentication()
    getUserRelatedParentEntityCalls.forEach { getUserRelatedParentEntityCall ->
        val authParentEntity = getUserRelatedParentEntityCall().checkEntityNotFound()
        checkAuthorization(authParentEntity.user.id, user.id)
    }

    val callResult = getTargetEntity().checkEntityNotFound()

    validationCall?.invoke(callResult)

    val dto = mappingCall(callResult)

    return onSuccess(dto).toResponseEntity()
}
