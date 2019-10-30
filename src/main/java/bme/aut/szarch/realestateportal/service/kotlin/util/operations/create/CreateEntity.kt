package bme.aut.szarch.realestateportal.service.kotlin.util.operations.create

import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Success
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun executeCreateOperation(
    validationCall: (() -> Unit)? = null,
    operationCall: () -> Unit
): ResponseEntity<Void> {
    return try {

        validationCall?.invoke()

        operationCall()

        Success<Void>(HttpStatus.CREATED).toResponseEntity()
    } catch (e: Exception) {
        DataTransferResult.Error(HttpStatus.BAD_REQUEST, e.message).toResponseEntity()
    }
}
