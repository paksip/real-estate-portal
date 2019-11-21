package bme.aut.szarch.realestateportal.service.kotlin.util.result


sealed class StorageMethodResult<out T : Any> {
    object Success : StorageMethodResult<Nothing>()
    data class Failed(val message: String, val exception: Throwable? = null) : StorageMethodResult<Nothing>()
}
