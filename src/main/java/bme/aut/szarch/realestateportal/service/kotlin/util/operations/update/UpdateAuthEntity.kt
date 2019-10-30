package bme.aut.szarch.realestateportal.service.kotlin.util.operations.create

import bme.aut.szarch.realestateportal.domain.User
import bme.aut.szarch.realestateportal.domain.kotlin.AbstractUserRelatedEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.checkAuthorization
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


fun <AuthParentEntityClass : AbstractUserRelatedEntity> executeUpdateOrDeleteWithAuthorization(
    getUserCall: () -> User?,
    getEntityCall: () -> AuthParentEntityClass?,
    validationCall: ((AuthParentEntityClass) -> Unit)? = null,
    operationCall: (AuthParentEntityClass) -> Unit
): ResponseEntity<Void> {
    return try {
        val user = getUserCall()
        check(user != null) { "The user not Authenticated!" }

        val authEntity = getEntityCall()
        check(authEntity != null) { "Does not exists any recourse with id" }
        checkAuthorization(authEntity.user.id, user.id)


        if (validationCall != null) {
            validationCall(authEntity)
        }

        operationCall(authEntity)

        DataTransferResult.Success<Void>(HttpStatus.OK).toResponseEntity()
    } catch (e: Exception) {
        DataTransferResult.Error(HttpStatus.BAD_REQUEST, e.message).toResponseEntity()
    }
}
