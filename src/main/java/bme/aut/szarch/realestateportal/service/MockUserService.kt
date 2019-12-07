package bme.aut.szarch.realestateportal.service

import bme.aut.szarch.realestateportal.domain.User
import bme.aut.szarch.realestateportal.repository.UserRepository
import bme.aut.szarch.realestateportal.service.kotlin.extensions.orNull
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*


@Service
open class MockUserService(
    val userRepository: UserRepository
) {

    open fun userWithAuthorities(): Optional<User> {

        var user = userRepository.findById(1).orNull()
        if (user == null) {
            val userTemp = User()
            userTemp.login = "admin"
            userTemp.firstName = "Administrator"
            userTemp.lastName = "Administrator"
            userTemp.email = "admin@localhost"
            userTemp.imageUrl = ""
            userTemp.langKey = "en"
            userTemp.password = "\$2a\$10agSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC"
            userTemp.resetKey = ""
            userTemp.resetDate = Instant.now()
            userTemp.activated = true
            userTemp.authorities = emptySet()
            userRepository.save(userTemp)
            user = userTemp
        }

        return Optional.of(user)
    }
}
