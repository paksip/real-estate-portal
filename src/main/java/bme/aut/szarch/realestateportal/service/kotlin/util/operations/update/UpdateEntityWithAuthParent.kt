package bme.aut.szarch.realestateportal.service.kotlin.util.operations.create

import bme.aut.szarch.realestateportal.domain.User
import bme.aut.szarch.realestateportal.domain.kotlin.AbstractJpaPersistable
import bme.aut.szarch.realestateportal.domain.kotlin.AbstractUserRelatedEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.checkAuthorization
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Error
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun <EntityClass : AbstractJpaPersistable, AuthParentEntityClass : AbstractUserRelatedEntity> executeUpdateOrDeleteWithAuthParent(
    getUserCall: () -> User?,
    getUserRelatedParentEntityCalls: List<() -> AuthParentEntityClass?>,
    getTargetEntityCall: () -> EntityClass?,
    validationCall: ((EntityClass) -> Unit)? = null,
    operationCall: (EntityClass) -> Unit
): ResponseEntity<Void> {
    return try {
        //Auth check
        val user = getUserCall()
        check(user != null) { "The user not Authenticated!" }

        getUserRelatedParentEntityCalls.forEach { getUserRelatedParentEntityCall ->
            val authParentEntity = getUserRelatedParentEntityCall()
            check(authParentEntity != null) { "The related authenticated entity does not exist" }
            checkAuthorization(authParentEntity.user.id, user.id)
        }

        val targetEntity = getTargetEntityCall()
        check(targetEntity != null) { "Does not exists any recourse with id" }

        if (validationCall != null) {
            validationCall(targetEntity)
        }

        operationCall(targetEntity)

        DataTransferResult.Success<Void>(HttpStatus.OK).toResponseEntity()
    } catch (e: Exception) {
        Error(HttpStatus.BAD_REQUEST, e.message).toResponseEntity()
    }
}
