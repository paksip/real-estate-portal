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
     fun createNewRealEstate(newRealEstate: NewRealEstate): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

     fun createNewReservation(realEstateId: Long, availableReservationTime: AvailableReservationTime): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

     fun deleteRealEstate(realEstateId: Long): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

     fun deleteReservation(realEstateId: Long, reservationId: Long): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

     fun getAllRealEstates(search: String, page: Int?, offset: Int?): ResponseEntity<List<RealEstate>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

     fun getAllReservation(realEstateId: Long): ResponseEntity<List<Reservation>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

     fun getRealEstateById(realEstateId: Long): ResponseEntity<RealEstateDetails> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

     fun getReservationDetails(realEstateId: Long, reservationId: Long): ResponseEntity<ReservationDetails> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

     fun newReservation(realEstateId: Long, reservationId: Long, newReservation: NewReservation): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

     fun updateRealEstate(realEstateId: Long, newRealEstate: NewRealEstate): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

     fun updateReservation(realEstateId: Long, reservationId: Long, availableReservationTime: AvailableReservationTime): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

     fun uploadFiles(realEstateId: Long): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
