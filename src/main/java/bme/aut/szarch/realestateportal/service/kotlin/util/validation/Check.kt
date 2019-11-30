package bme.aut.szarch.realestateportal.service.kotlin.util.validation

import bme.aut.szarch.realestateportal.domain.User
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.BadCredentialsException


fun User?.checkAuthentication(): User {
    if (this == null) {
        throw BadCredentialsException("User does not exist")
    }
    return this
}

inline fun <reified T : Any> T?.checkEntityNotFound(): T {
    if (this == null) {
            throw NoSuchElementException("Entity Not Found with name : ${T::class.java.name}")
    }
    return this
}

fun checkAuthorization(relatedUserId: Long, userId: Long) {
    if (relatedUserId != userId) {
        throw AccessDeniedException("Only authorized users can access the resource")
    }
}
