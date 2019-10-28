package bme.aut.szarch.realestateportal.config

import bme.aut.szarch.realestateportal.repository.kotlin.RealEstateRepository
import bme.aut.szarch.realestateportal.service.MailService
import bme.aut.szarch.realestateportal.service.UserService
import bme.aut.szarch.realestateportal.service.kotlin.StorageServiceImp
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.PageRequest
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled


@Configuration
@EnableScheduling
open class ScheduleConfig(
    private val realEstateRepository: RealEstateRepository,
    private val userService: UserService,
    private val mailService: MailService
) {

    companion object {
        const val WEEK_IN_MILLISEC: Long = 604_800_000
    }

    @Scheduled(fixedDelay = WEEK_IN_MILLISEC)
    fun sendSpectatorsCountToUsers() {
        userService.getAllManagedUsers(PageRequest.of(0, 100)).content
            .forEach { user ->
                val contentStringBuilder = StringBuilder()

                realEstateRepository
                    .findByUserId(user.id, PageRequest.of(0, 100)).content
                    .forEach {
                        contentStringBuilder.append("Real-estates: ${it.name} SpectatorsCount: ${it.spectatorsCount} \n")
                    }
                mailService.sendEmail(
                    user.email,
                    "SpectatorsCount",
                    contentStringBuilder.toString(),
                    false,
                    false
                )

            }
    }
}
