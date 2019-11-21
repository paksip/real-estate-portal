package bme.aut.szarch.realestateportal.service.kotlin.util.result

import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Success
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component


@Component
sealed class DataTransferResult<out T : Any> {
    data class Success<out T : Any>(
        val successCode: HttpStatus,
        val result: T? = null,
        val headers: HttpHeaders? = null
    ) : DataTransferResult<T>()
}


fun <T : Any> DataTransferResult<T>.toResponseEntity(): ResponseEntity<T> {
    return when (this) {
        is Success -> {
            if (result != null) {
                if (headers != null) {
                    ResponseEntity.status(successCode).headers(headers).body(result)
                } else {
                    ResponseEntity.status(successCode).body(result)
                }
            } else {
                ResponseEntity.status(successCode).build()
            }
        }
    }
}





