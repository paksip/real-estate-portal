package bme.aut.szarch.realestateportal.domain

import java.time.OffsetDateTime
import javax.persistence.Embeddable


@Embeddable
data class AvailableReservationTimeEntity(
    val from: OffsetDateTime,
    val to: OffsetDateTime
)

