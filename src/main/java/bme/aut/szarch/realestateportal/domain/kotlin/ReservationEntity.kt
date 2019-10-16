package bme.aut.szarch.realestateportal.domain.kotlin

import java.time.OffsetDateTime
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.Pattern

@Entity
data class ReservationEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val from: OffsetDateTime,

    val to: OffsetDateTime,

    @Email
    val emailAddress: String?,

    @Pattern(regexp = "(^$|[0-9]{10})")
    val phoneNumber: String?,

    val message: String?,

    val userName: String?,

    @ManyToOne
    @JoinColumn(name = "real_estate_id", nullable = false)
    val realEstate: RealEstateEntity,

    val isFree: Boolean = true
)
