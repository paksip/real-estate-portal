package bme.aut.szarch.realestateportal.service.kotlin.util.operations.create

import bme.aut.szarch.realestateportal.domain.User
import bme.aut.szarch.realestateportal.domain.kotlin.AbstractUserRelatedEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Success
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.validation.checkAuthentication
import bme.aut.szarch.realestateportal.service.kotlin.util.validation.checkAuthorization
import bme.aut.szarch.realestateportal.service.kotlin.util.validation.checkEntityNotFound
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

inline fun <reified ParentEntity : AbstractUserRelatedEntity> executeCreateWithAutParent(
    getUserCall: () -> User?,
    getParentEntityCall: () -> ParentEntity?,
    noinline validationCheck: ((ParentEntity) -> Unit)? = null,
    operationCall: (ParentEntity) -> Unit
): ResponseEntity<Void> {
    val user = getUserCall().checkAuthentication()
    val authParentEntity = getParentEntityCall().checkEntityNotFound()
    checkAuthorization(authParentEntity.user.id, user.id)

    validationCheck?.invoke(authParentEntity)

    operationCall(authParentEntity)

    return Success<Void>(HttpStatus.CREATED).toResponseEntity()
}
