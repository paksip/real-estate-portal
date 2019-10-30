package bme.aut.szarch.realestateportal.service.kotlin.util.operations.create

import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun <ParentEntity : Any> executeCreateWithParent(
    getParentEntityCall: () -> ParentEntity?,
    validationCall: ((ParentEntity) -> Unit)? = null,
    operationCall: (ParentEntity) -> Unit
): ResponseEntity<Void> {
    return try {

        val parentEntity = getParentEntityCall()
        check(parentEntity != null) { "Does not exists any recourse with id" }

        validationCall?.invoke(parentEntity)

        operationCall(parentEntity)

        DataTransferResult.Success<Void>(HttpStatus.CREATED).toResponseEntity()
    } catch (e: Exception) {
        DataTransferResult.Error(HttpStatus.BAD_REQUEST, e.message).toResponseEntity()
    }
}
