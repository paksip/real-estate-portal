package bme.aut.szarch.realestateportal.service.kotlin.util.operations.create

import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.validation.checkEntityNotFound
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

inline fun <reified ParentEntity : Any> executeCreateWithParent(
    getParentEntityCall: () -> ParentEntity?,
    noinline validationCall: ((ParentEntity) -> Unit)? = null,
    operationCall: (ParentEntity) -> Unit
): ResponseEntity<Void> {
    val parentEntity = getParentEntityCall().checkEntityNotFound()

    validationCall?.invoke(parentEntity)

    operationCall(parentEntity)

    return DataTransferResult.Success<Void>(HttpStatus.CREATED).toResponseEntity()
}
