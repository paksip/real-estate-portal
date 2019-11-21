package bme.aut.szarch.realestateportal.domain.kotlin

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.Pattern

@Entity
@Table(schema = "realestateportal", name = "realestate_reservation")
data class ReservationEntity(

    @Column(name = "reservation_from")
    var from: String,

    @Column(name = "reservation_to")
    var to: String,

    @Email
    var emailAddress: String?,

    @Pattern(regexp = "(^$|[0-9]{10})")
    var phoneNumber: String?,

    var message: String?,

    var userName: String?,

    @ManyToOne
    @JoinColumn(name = "real_estate_id")
    var realEstate: RealEstateEntity,

    var isFree: Boolean = true
) : AbstractJpaPersistable()
