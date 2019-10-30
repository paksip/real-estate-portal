package bme.aut.szarch.realestateportal.domain.kotlin

import java.time.OffsetDateTime
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.Pattern

@Entity
@Table(schema = "realestateportal", name = "realestate_reservation")
data class ReservationEntity(

    @Column(name = "reservation_from")
    val from: OffsetDateTime,

    @Column(name = "reservation_to")
    val to: OffsetDateTime,

    @Email
    val emailAddress: String?,

    @Pattern(regexp = "(^$|[0-9]{10})")
    val phoneNumber: String?,

    val message: String?,

    val userName: String?,

    @ManyToOne
    @JoinColumn(name = "real_estate_id")
    val realEstate: RealEstateEntity,

    val isFree: Boolean = true
) : AbstractJpaPersistable()
