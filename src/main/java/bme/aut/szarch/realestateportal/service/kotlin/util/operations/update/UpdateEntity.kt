package bme.aut.szarch.realestateportal.service.kotlin.util.operations.create

import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


// region UpdateEntity
fun <EntityClass : Any> executeUpdateOrDelete(
    databaseGetCall: () -> EntityClass?,
    validationCall: ((EntityClass) -> Unit)? = null,
    operationCall: (EntityClass) -> Unit
): ResponseEntity<Void> {
    return try {
        val entity = databaseGetCall()
        check(entity != null) { "Does not exists any recourse with id" }

        validationCall?.invoke(entity)

        operationCall(entity)

        DataTransferResult.Success<Void>(HttpStatus.OK).toResponseEntity()
    } catch (e: Exception) {
        DataTransferResult.Error(HttpStatus.BAD_REQUEST, e.message).toResponseEntity()
    }
}
//endregion
