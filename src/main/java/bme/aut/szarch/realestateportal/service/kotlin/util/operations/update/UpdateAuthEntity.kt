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


inline fun <reified AuthParentEntityClass : AbstractUserRelatedEntity> executeUpdateOrDeleteWithAuthorization(
    getUserCall: () -> User?,
    getEntityCall: () -> AuthParentEntityClass?,
    noinline validationCall: ((AuthParentEntityClass) -> Unit)? = null,
    operationCall: (AuthParentEntityClass) -> Unit
): ResponseEntity<Void> {
    val user = getUserCall().checkAuthentication()
    val authEntity = getEntityCall().checkEntityNotFound()
    checkAuthorization(authEntity.user.id, user.id)

    validationCall?.invoke(authEntity)

    operationCall(authEntity)

    return Success<Void>(HttpStatus.OK).toResponseEntity()
}


