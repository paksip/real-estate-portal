package bme.aut.szarch.realestateportal.domain

import bme.aut.szarch.realestateportal.service.dto.RealEstateDetails.CategoryEnum
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

@Entity
data class RealEstateEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @NotNull
    @NotEmpty
    val name: String,

    @NotNull
    @NotEmpty
    val description: String,

    @Embedded
    val location: LocationEntity,

    @Enumerated(EnumType.STRING)
    val category: CategoryEnum,

    val spectatorsCount: Long = 0,

    @ElementCollection
    val availableReservationTimeEntities: MutableCollection<AvailableReservationTimeEntity>,

    val squareMeter: Int,

    val price: Int,

    val numberOfRooms: Int,

    val hasBalcony: Boolean,

    val hasAircondition: Boolean,

    @Pattern(regexp = "(^$|[0-9]{10})")
    val ownerPhoneNumber: String,

    @OneToMany(mappedBy = "realEstate")
    @Cascade(CascadeType.DELETE)
    val reservations: List<ReservationEntity> = emptyList(),

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
)
