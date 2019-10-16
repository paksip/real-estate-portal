package bme.aut.szarch.realestateportal.service

import bme.aut.szarch.realestateportal.service.DataTransferResult.Failure
import bme.aut.szarch.realestateportal.service.DataTransferResult.Success
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

sealed class DataTransferResult<out T : Any> {
    data class Success<out T : Any>(
        val successCode: HttpStatus,
        val result: T? = null,
        val headers: HttpHeaders? = null
    ) : DataTransferResult<T>()

    data class Failure(val failureCode: HttpStatus, val message: String) : DataTransferResult<Nothing>()
}


fun <T : Any> DataTransferResult<T>.toResponseEntity(): ResponseEntity<Any> {
    return when (this) {
        is Success -> ResponseEntity.status(successCode).headers(headers).body(result)
        is Failure -> ResponseEntity.status(failureCode).body(message)
    }
}
