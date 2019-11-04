package bme.aut.szarch.realestateportal.service.kotlin.util.operations.create

import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Success
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun executeCreateOperation(
    validationCall: (() -> Unit)? = null,
    operationCall: () -> Unit
): ResponseEntity<Void> {
    validationCall?.invoke()

    operationCall()

    return Success<Void>(HttpStatus.CREATED).toResponseEntity()
}
