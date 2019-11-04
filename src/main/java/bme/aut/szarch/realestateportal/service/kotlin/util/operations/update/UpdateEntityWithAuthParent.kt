package bme.aut.szarch.realestateportal.service.kotlin.util.operations.update

import bme.aut.szarch.realestateportal.domain.User
import bme.aut.szarch.realestateportal.domain.kotlin.AbstractUserRelatedEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Success
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.validation.checkAuthentication
import bme.aut.szarch.realestateportal.service.kotlin.util.validation.checkAuthorization
import bme.aut.szarch.realestateportal.service.kotlin.util.validation.checkEntityNotFound
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

inline fun <reified EntityClass : Any, reified AuthParentEntityClass : AbstractUserRelatedEntity> executeUpdateOrDeleteWithAuthParent(
    getUserCall: () -> User?,
    getUserRelatedParentEntityCalls: List<() -> AuthParentEntityClass?>,
    getTargetEntityCall: () -> EntityClass?,
    noinline validationCall: ((EntityClass) -> Unit)? = null,
    operationCall: (EntityClass) -> Unit
): ResponseEntity<Void> {
    val user = getUserCall().checkAuthentication()

    getUserRelatedParentEntityCalls.forEach { getUserRelatedParentEntityCall ->
        val authParentEntity = getUserRelatedParentEntityCall().checkEntityNotFound()
        checkAuthorization(authParentEntity.user.id, user.id)
    }

    val targetEntity = getTargetEntityCall().checkEntityNotFound()

    validationCall?.invoke(targetEntity)

    operationCall(targetEntity)

    return Success<Void>(HttpStatus.OK).toResponseEntity()
}
