package bme.aut.szarch.realestateportal.service.kotlin.mapper

import bme.aut.szarch.realestateportal.domain.User
import bme.aut.szarch.realestateportal.domain.kotlin.LocationEntity
import bme.aut.szarch.realestateportal.domain.kotlin.RealEstateEntity
import bme.aut.szarch.realestateportal.domain.kotlin.ReservationEntity
import bme.aut.szarch.realestateportal.service.kotlin.dto.*


//region realEstate
internal fun RealEstateEntity.toRealEstateDTO(filPaths: List<String>): RealEstateDTO {
    return RealEstateDTO().also { realEstateDTO ->
        realEstateDTO.id = this.id
        realEstateDTO.name = this.name
        realEstateDTO.numberOfRooms = this.numberOfRooms
        realEstateDTO.price = this.price
        realEstateDTO.squareMeter = this.squareMeter
        realEstateDTO.spectatorsCount = this.spectatorsCount
        realEstateDTO.filePaths = filPaths
    }
}

fun RealEstateEntity.toRealEstateDetailsDTO(filPaths: List<String>): RealEstateDetailsDTO {
    return RealEstateDetailsDTO().also { realEstateDetailsDTO ->
        realEstateDetailsDTO.name = this.name
        realEstateDetailsDTO.description = this.description
        realEstateDetailsDTO.locationDTO = this.location.toLocationDTO()
        realEstateDetailsDTO.category = this.category
        realEstateDetailsDTO.spectatorsCount = this.spectatorsCount
        realEstateDetailsDTO.squareMeter = this.squareMeter
        realEstateDetailsDTO.price = this.price
        realEstateDetailsDTO.numberOfRooms = this.numberOfRooms
        realEstateDetailsDTO.hasBalncony = this.hasBalcony
        realEstateDetailsDTO.hasAirCondition = this.hasAircondition
        realEstateDetailsDTO.ownerPhoneNumber = this.ownerPhoneNumber
        realEstateDetailsDTO.filePaths = filPaths
    }
}

fun RealEstateEntity.incrementSpectatorsCount(): RealEstateEntity {
    spectatorsCount += 1
    return this
}

fun RealEstateEntity.toUpdatedRealEstateEntity(newRealEstateDTO: NewRealEstateDTO): RealEstateEntity {
        name = newRealEstateDTO.name
        description = newRealEstateDTO.description
        category = newRealEstateDTO.category
        location = newRealEstateDTO.location.toLocationEntity()
        squareMeter = newRealEstateDTO.squareMeter
        price = newRealEstateDTO.price
        numberOfRooms = newRealEstateDTO.numberOfRooms
        hasBalcony = newRealEstateDTO.hasBalncony
        hasAircondition = newRealEstateDTO.hasBalncony
        ownerPhoneNumber = newRealEstateDTO.ownerPhoneNumber
    return this
}

fun NewRealEstateDTO.toRealEstateEntity(user: User): RealEstateEntity {
    val realEstateEntity = RealEstateEntity(
        name = this.name,
        description = this.description,
        location = this.location.toLocationEntity(),
        category = this.category,
        squareMeter = this.squareMeter,
        price = this.price,
        numberOfRooms = this.numberOfRooms,
        hasBalcony = this.hasBalncony,
        hasAircondition = this.hasAirCondition,
        ownerPhoneNumber = this.ownerPhoneNumber,
        reservations = emptyList()
    )
    realEstateEntity.user = user
    return realEstateEntity
}
//endregion


//region Location
fun LocationDTO.toLocationEntity(): LocationEntity {
    return LocationEntity(
        lat = this.lat.toInt(),
        lon = this.lon.toInt(),
        locationName = this.locationName
    )
}

fun LocationEntity.toLocationDTO(): LocationDTO {
    return LocationDTO().also { locationDTO ->
        locationDTO.lat = this.lat.toBigDecimal()
        locationDTO.lon = this.lon.toBigDecimal()
        locationDTO.locationName = this.locationName
    }
}
//endregion

//region Reservation
fun ReservationEntity.toReservationDTO(): ReservationDTO {
    return ReservationDTO().also { reservationDTO ->
        reservationDTO.id = this.id
        reservationDTO.from = this.from
        reservationDTO.to = this.to
        reservationDTO.isFree = this.isFree
    }
}

fun ReservationEntity.toReservationDetailsDTO(): ReservationDetailsDTO {
    return ReservationDetailsDTO().also { reservationDetailsDTO ->
        reservationDetailsDTO.from = this.from
        reservationDetailsDTO.to = this.to
        reservationDetailsDTO.emailAddress = this.emailAddress
        reservationDetailsDTO.phoneNumber = this.phoneNumber
        reservationDetailsDTO.message = this.message
        reservationDetailsDTO.userName = this.userName
    }
}

fun ReservationEntity.toReservedReservationEntity(
    newReservationDTO: NewReservationDTO
): ReservationEntity {
        emailAddress = newReservationDTO.email
        phoneNumber = newReservationDTO.phoneNumber
        message = newReservationDTO.message
        userName = newReservationDTO.userName
        isFree = false
    return this
}

fun AvailableReservationTimeDTO.toFreeReservationEntity(realEstateEntity: RealEstateEntity): ReservationEntity {
    return ReservationEntity(
        from = this.from,
        to = this.to,
        emailAddress = null,
        phoneNumber = null,
        userName = null,
        message = null,
        realEstate = realEstateEntity,
        isFree = true
    )
}

fun ReservationEntity.toUpdatedFreeReservationEntity(availableReservationTimeDTO: AvailableReservationTimeDTO): ReservationEntity {
        from = availableReservationTimeDTO.from
        to = availableReservationTimeDTO.to
    return this
}
//endregion
