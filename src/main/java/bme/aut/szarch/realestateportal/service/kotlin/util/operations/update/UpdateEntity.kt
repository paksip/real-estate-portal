package bme.aut.szarch.realestateportal.service.kotlin.util.operations.update

import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Success
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.validation.checkEntityNotFound
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

inline fun <reified EntityClass : Any> executeUpdateOrDelete(
    databaseGetCall: () -> EntityClass?,
    noinline validationCall: ((EntityClass) -> Unit)? = null,
    operationCall: (EntityClass) -> Unit
): ResponseEntity<Void> {
    val entity = databaseGetCall().checkEntityNotFound()

    validationCall?.invoke(entity)

    operationCall(entity)

    return Success<Void>(HttpStatus.OK).toResponseEntity()
}
