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
    var name: String,

    @NotNull
    @NotEmpty
    var description: String,

    @Embedded
    var location: LocationEntity,

    @Enumerated(EnumType.STRING)
    var category: CategoryEnum,

    var spectatorsCount: Long = 0,

    var squareMeter: Int,

    var price: Int,

    var numberOfRooms: Int,

    var hasBalcony: Boolean,

    var hasAircondition: Boolean,

    @Pattern(regexp = "(^$|[0-9]{10})")
    var ownerPhoneNumber: String,

    @OneToMany(mappedBy = "realEstate")
    @Cascade(CascadeType.DELETE)
    var reservations: List<ReservationEntity>

) : AbstractUserRelatedEntity()
