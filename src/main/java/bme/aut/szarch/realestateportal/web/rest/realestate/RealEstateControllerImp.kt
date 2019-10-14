package bme.aut.szarch.realestateportal.web.rest.realestate

import bme.aut.szarch.realestateportal.service.RealEstateService
import bme.aut.szarch.realestateportal.service.dto.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/realestates")
class RealEstateControllerImp(
    val realEStateService: RealEstateService
) : RealEstateController {
    override fun createNewRealEstate(newRealEstate: NewRealEstate): ResponseEntity<Void> {
       return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun createNewReservation(realEstateId: Long, availableReservationTime: AvailableReservationTime): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun deleteRealEstate(realEstateId: Long): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun deleteReservation(realEstateId: Long, reservationId: Long): ResponseEntity<Void> {
              return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun getAllRealEstates(search: String, page: Int?, offset: Int?): ResponseEntity<List<RealEstate>> {
              return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun getAllReservation(realEstateId: Long): ResponseEntity<List<Reservation>> {
              return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun getRealEstateById(realEstateId: Long): ResponseEntity<RealEstateDetails> {
              return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun getReservationDetails(realEstateId: Long, reservationId: Long): ResponseEntity<ReservationDetails> {
              return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun newReservation(realEstateId: Long, reservationId: Long, newReservation: NewReservation): ResponseEntity<Void> {
              return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun updateRealEstate(realEstateId: Long, newRealEstate: NewRealEstate): ResponseEntity<Void> {
              return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun updateReservation(realEstateId: Long, reservationId: Long, availableReservationTime: AvailableReservationTime): ResponseEntity<Void> {
              return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun uploadFiles(realEstateId: Long): ResponseEntity<Void> {
              return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

}
