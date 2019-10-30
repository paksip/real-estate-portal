package bme.aut.szarch.realestateportal.service.kotlin.util.operations.create

import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Error
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


fun <EntityClass : Any, DTOClass : Any> executeRead(
    getTargetEntity: () -> EntityClass?,
    validationCall: ((EntityClass) -> Unit)? = null,
    mappingCall: (EntityClass) -> DTOClass,
    onSuccess: (DTOClass) -> DataTransferResult<DTOClass>
): ResponseEntity<DTOClass> {
    return try {
        val entity = getTargetEntity()

        check(entity != null) { "Does not exists any recourse with id" }

        validationCall?.invoke(entity)

        val dto = mappingCall(entity)

        onSuccess(dto).toResponseEntity()
    } catch (e: Exception) {
        Error(HttpStatus.BAD_REQUEST, e.message).toResponseEntity()
    }
}


