package bme.aut.szarch.realestateportal.service.kotlin.util.operations.read

import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.validation.checkEntityNotFound
import org.springframework.http.ResponseEntity


inline fun <reified EntityClass : Any, DTOClass : Any> executeRead(
    getTargetEntity: () -> EntityClass?,
    noinline validationCall: ((EntityClass) -> Unit)? = null,
    mappingCall: (EntityClass) -> DTOClass,
    onSuccess: (DTOClass) -> DataTransferResult<DTOClass>
): ResponseEntity<DTOClass> {
    val entity = getTargetEntity().checkEntityNotFound()

    validationCall?.invoke(entity)

    val dto = mappingCall(entity)

    return onSuccess(dto).toResponseEntity()
}

