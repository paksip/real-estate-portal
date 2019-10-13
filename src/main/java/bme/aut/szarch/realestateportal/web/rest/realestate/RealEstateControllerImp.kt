package bme.aut.szarch.realestateportal.web.rest.realestate

import bme.aut.szarch.realestateportal.service.RealEstateService
import bme.aut.szarch.realestateportal.service.dto.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/realestates")
class RealEstateControllerImp(
    val realEStateService: RealEstateService
) : RealEstateController {
    override fun getReservationDetails(realEstateId: Long, reservationId: Long): ResponseEntity<ReservationDetails> {
        return realEStateService.getReservationDetails(realEstateId, reservationId)
    }

    override fun updateRealEstate(realEstateId: Long, newRealEstate: NewRealEstate): ResponseEntity<Void> {
        return realEStateService.updateRealEstate(realEstateId, newRealEstate)
    }

    override fun updateReservation(realEstateId: Long, reservationId: Long, reservationDetails: ReservationDetails): ResponseEntity<Void> {
        return realEStateService.updateReservation(realEstateId, reservationId, reservationDetails)
    }

    override fun createNewRealEstate(newRealEstate: NewRealEstate): ResponseEntity<Void> {
        return realEStateService.createNewRealEstate(newRealEstate)
    }

    override fun createNewReservation(realEstateId: Long, reservationDetails: ReservationDetails): ResponseEntity<Void> {
        return realEStateService.createNewReservation(realEstateId, reservationDetails)
    }

    override fun deleteRealEstate(realEstateId: Long): ResponseEntity<Void> {
        return realEStateService.deleteRealEstate(realEstateId)
    }

    override fun deleteReservation(realEstateId: Long, reservationId: Long): ResponseEntity<Void> {
        return realEStateService.deleteReservation(realEstateId, reservationId)
    }

    override fun getAllRealEstates(search: String): ResponseEntity<List<RealEstate>> {
        return realEStateService.getAllRealEstates(search)
    }

    override fun getAllReservation(realEstateId: Long): ResponseEntity<List<Reservation>> {
        return realEStateService.getAllReservation(realEstateId)
    }

    override fun getRealEstateById(realEstateId: Long): ResponseEntity<RealEstateDetails> {
        return realEStateService.getRealEstateById(realEstateId)
    }
}
