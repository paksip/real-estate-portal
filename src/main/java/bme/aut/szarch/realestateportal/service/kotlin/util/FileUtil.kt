package bme.aut.szarch.realestateportal.service.kotlin.util

import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import java.nio.file.Path

inline fun <reified T : Any> Path.toUrl(): String {
    return MvcUriComponentsBuilder.fromMethodName(T::class.java,
        "downloadFile", fileName.toString()).build().toString()
}
