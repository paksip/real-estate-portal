package bme.aut.szarch.realestateportal.service.kotlin.util.operations.create

import bme.aut.szarch.realestateportal.domain.User
import bme.aut.szarch.realestateportal.domain.kotlin.AbstractUserRelatedEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.checkAuthorization
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun <ParentEntity : AbstractUserRelatedEntity> executeCreateWithAutParent(
    getUserCall: () -> User?,
    getParentEntityCall: () -> ParentEntity?,
    validationCheck: ((ParentEntity) -> Unit)? = null,
    operationCall: (ParentEntity) -> Unit
): ResponseEntity<Void> {
    return try {
        val user = getUserCall()
        check(user != null) { "The user not Authenticated!" }

        val authParentEntity = getParentEntityCall()
        check(authParentEntity != null) { "The related authenticated entity does not exist" }
        checkAuthorization(authParentEntity.user.id, user.id)


        validationCheck?.invoke(authParentEntity)

        operationCall(authParentEntity)

        DataTransferResult.Success<Void>(HttpStatus.CREATED).toResponseEntity()
    } catch (e: Exception) {
        DataTransferResult.Error(HttpStatus.BAD_REQUEST, e.message).toResponseEntity()
    }
}
