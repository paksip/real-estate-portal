package bme.aut.szarch.realestateportal.service

import bme.aut.szarch.realestateportal.repository.RealEstateRepository
import bme.aut.szarch.realestateportal.repository.ReservationRepository
import bme.aut.szarch.realestateportal.security.SecurityUtils
import bme.aut.szarch.realestateportal.service.dto.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class RealEstateService(
    val realEstateRepository: RealEstateRepository,
    val reservationRepository: ReservationRepository
) {
    fun getReservationDetails(realEstateId: Long, reservationId: Long): ResponseEntity<ReservationDetails> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    fun updateRealEstate(realEstateId: Long, newRealEstate: NewRealEstate): ResponseEntity<Void> {
        SecurityUtils.getCurrentUserLogin()
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    }

    fun updateReservation(realEstateId: Long, reservationId: Long, reservationDetails: ReservationDetails): ResponseEntity<Void> {

        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    }

    fun createNewRealEstate(newRealEstate: NewRealEstate): ResponseEntity<Void> {

        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    fun createNewReservation(realEstateId: Long, reservationDetails: ReservationDetails): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    fun deleteRealEstate(realEstateId: Long): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    fun deleteReservation(realEstateId: Long, reservationId: Long): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    fun getAllRealEstates(search: String): ResponseEntity<List<RealEstate>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    fun getAllReservation(realEstateId: Long): ResponseEntity<List<Reservation>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    fun getRealEstateById(realEstateId: Long): ResponseEntity<RealEstateDetails> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
