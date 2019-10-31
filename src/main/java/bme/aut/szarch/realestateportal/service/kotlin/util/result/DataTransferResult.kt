package bme.aut.szarch.realestateportal.service.kotlin.util.result

import bme.aut.szarch.realestateportal.service.UserService
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Error
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Success
import org.springframework.beans.factory.annotation.Autowired
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

    data class Error(val failureCode: HttpStatus? = null, val message: String? = null) : DataTransferResult<Nothing>()
}


fun <T : Any> DataTransferResult<T>.toResponseEntity(): ResponseEntity<T> {
    return when (this) {
        is Success -> {
            result?.let {
                headers?.let {
                    ResponseEntity.status(successCode).headers(headers).body(result)
                }
                ResponseEntity.status(successCode).body(result)
            }
            ResponseEntity.status(successCode).build()
        }
        //TODO Replace with Autsoft exception
        is Error -> throw RuntimeException(message)
    }
}





