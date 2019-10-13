package bme.aut.szarch.realestateportal.service.mapper

import bme.aut.szarch.realestateportal.domain.*
import bme.aut.szarch.realestateportal.service.dto.*


//region realEstate


internal fun RealEstateEntity.toRealEstateDTO(filPaths: List<String>): RealEstate {
    return RealEstate().also { realEstateDTO ->
        realEstateDTO.id = this.id
        realEstateDTO.name = this.name
        realEstateDTO.numberOfRooms = this.numberOfRooms
        realEstateDTO.price = this.price
        realEstateDTO.squareMeter = this.squareMeter
        realEstateDTO.spectatorsCount = this.spectatorsCount
        realEstateDTO.filePaths = filPaths
    }
}

fun RealEstateEntity.toRealEstateDetailsDTO(filPaths: List<String>): RealEstateDetails {
    return RealEstateDetails().also { realEstateDetailsDTO ->
        realEstateDetailsDTO.name = this.name
        realEstateDetailsDTO.description = this.description
        realEstateDetailsDTO.location = this.location.toLocationDTO()
        realEstateDetailsDTO.category = this.category
        realEstateDetailsDTO.spectatorsCount = this.spectatorsCount
        realEstateDetailsDTO.availableReservationTimes = this.availableReservationTimeEntities.map(AvailableReservationTimeEntity::toAvailableReservationTimeDTO)
        realEstateDetailsDTO.squareMeter = this.squareMeter
        realEstateDetailsDTO.price = this.price
        realEstateDetailsDTO.numberOfRooms = this.numberOfRooms
        realEstateDetailsDTO.hasBalncony = this.hasBalcony
        realEstateDetailsDTO.hasAirCondition = this.hasAircondition
        realEstateDetailsDTO.ownerPhoneNumber = this.ownerPhoneNumber
        realEstateDetailsDTO.filePaths = filPaths
        realEstateDetailsDTO.reservations = this.reservations.map(ReservationEntity::toReservationDTO)
    }
}


fun NewRealEstate.toRealEstateEntity(user: User): RealEstateEntity {
    return RealEstateEntity(
        name = this.name,
        description = this.description,
        location = this.location.toLocationEntity(),
        category = this.category,
        availableReservationTimeEntities = this.availableReservationTimes
            .map(AvailableReservationTime::toAvailableReservationTimeEntity)
            .toMutableList(),
        squareMeter = this.squareMeter,
        price = this.price,
        numberOfRooms = this.numberOfRooms,
        hasBalcony = this.hasBalncony,
        hasAircondition = this.hasAirCondition,
        ownerPhoneNumber = this.ownerPhoneNumber,
        user = user
    )
}
//endregion


//region AvailableReservationTime
fun AvailableReservationTimeEntity.toAvailableReservationTimeDTO(): AvailableReservationTime {
    return AvailableReservationTime().also { availableReservationTime ->
        availableReservationTime.from = this.from
        availableReservationTime.to = this.to
    }
}

fun AvailableReservationTime.toAvailableReservationTimeEntity(): AvailableReservationTimeEntity {
    return AvailableReservationTimeEntity(
        from = this.from,
        to = this.to
    )
}
//endregion


//region Location
fun Location.toLocationEntity(): LocationEntity {
    return LocationEntity(
        lat = this.lat.toInt(),
        lon = this.lon.toInt(),
        locationName = this.locationName
    )
}

fun LocationEntity.toLocationDTO(): Location {
    return Location().also { locationDTO ->
        locationDTO.lat = this.lat.toBigDecimal()
        locationDTO.lon = this.lon.toBigDecimal()
        locationDTO.locationName = this.locationName
    }
}
//endregion


//region Reservation
fun ReservationEntity.toReservationDTO(): Reservation {
    return Reservation().also { reservationDTO ->
        reservationDTO.id = this.id
        reservationDTO.from = this.from
        reservationDTO.to = this.to
    }
}

fun ReservationEntity.toReservationDetailsDTO(): ReservationDetails {
    return ReservationDetails().also { reservationDetailsDTO ->
        reservationDetailsDTO.from = this.from
        reservationDetailsDTO.to = this.to
        reservationDetailsDTO.emailAddress = this.emailAddress
        reservationDetailsDTO.phoneNumber = this.phoneNumber
        reservationDetailsDTO.message = this.message
        reservationDetailsDTO.userName = this.userName
    }
}

fun ReservationDetails.toReservationEntity(realEstateEntity: RealEstateEntity): ReservationEntity {
    return ReservationEntity(
        from = this.from,
        to = this.to,
        emailAddress = this.emailAddress,
        phoneNumber = this.phoneNumber,
        message = this.message,
        userName = this.userName,
        realEstate = realEstateEntity
    )
}


//endregion

