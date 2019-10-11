package bme.aut.szarch.realestateportal.web.rest.realestate

import bme.aut.szarch.realestateportal.web.api.model.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/realestates")
class RealEstateControllerImp : RealEstateController {

    override fun createNewRealEstate(newRealEstate: NewRealEstate): ResponseEntity<Void> {
        return ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun getAllRealEstates(search: String): ResponseEntity<List<RealEstate>> {
        return ResponseEntity<List<RealEstate>>(HttpStatus.NOT_IMPLEMENTED)

    }

    override fun getRealEstateById(realEstateId: String): ResponseEntity<RealEstateDetails> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    }

    override fun updateRealEstate(realEstateId: String, newRealEstate: NewRealEstate): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    }

    override fun deleteRealEstate(realEstateId: String): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    }

    override fun createNewReservation(realEstateId: String, reservationDetails: ReservationDetails): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    }

    override fun getAllReservation(realEstateId: String): ResponseEntity<List<Reservation>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    }

    override fun getReservationDetails(realEstateId: String, reservationId: String): ResponseEntity<ReservationDetails> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    }

    override fun deleteReservation(realEstateId: String, reservationId: String): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    }

    override fun updateReservation(realEstateId: String, reservationId: String, reservationDetails: ReservationDetails): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
