package bme.aut.szarch.realestateportal.domain.kotlin

import bme.aut.szarch.realestateportal.service.kotlin.dto.NewRealEstateDTO.CategoryEnum
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

@Entity
@Table(schema = "realestateportal", name = "realestate")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
data class RealEstateEntity(
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

    var spectatorsCount: Long = 0,

    val squareMeter: Int,

    val price: Int,

    val numberOfRooms: Int,

    val hasBalcony: Boolean,

    val hasAircondition: Boolean,

    @Pattern(regexp = "(^$|[0-9]{10})")
    val ownerPhoneNumber: String,

    @OneToMany(mappedBy = "realEstate")
    @Cascade(CascadeType.DELETE)
    val reservations: List<ReservationEntity>

) : AbstractUserRelatedEntity()
