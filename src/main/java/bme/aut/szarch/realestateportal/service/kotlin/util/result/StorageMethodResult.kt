package bme.aut.szarch.realestateportal.service.kotlin.util.result


sealed class StorageMethodResult<out T : Any> {
    data class SuccessWithResult<T : Any>(val result: T) : StorageMethodResult<T>()
    object Success : StorageMethodResult<Nothing>()
    data class Failed(val message: String, val exception: Throwable? = null) : StorageMethodResult<Nothing>()
}
