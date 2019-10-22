package bme.aut.szarch.realestateportal.service.kotlin.util

import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import java.nio.file.Path
import net.logstash.logback.argument.StructuredArguments.r


inline fun <reified T : Any> Path.toUrl(): String {
    return MvcUriComponentsBuilder.fromMethodName(T::class.java,
        "downloadFile", fileName.toString()).build().toString()
}

fun String.addIdentifier(identifier: String) =
    this.substring(0, this.lastIndexOf('.')) + identifier + this.substring(this.lastIndexOf('.'))

