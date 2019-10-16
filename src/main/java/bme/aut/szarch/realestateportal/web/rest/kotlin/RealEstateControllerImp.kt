package bme.aut.szarch.realestateportal.web.rest.kotlin

import bme.aut.szarch.realestateportal.service.kotlin.RealEstateService
import bme.aut.szarch.realestateportal.service.kotlin.StorageService
import bme.aut.szarch.realestateportal.service.kotlin.dto.AvailableReservationTimeDTO
import bme.aut.szarch.realestateportal.service.kotlin.dto.NewRealEstateDTO
import bme.aut.szarch.realestateportal.service.kotlin.dto.NewReservationDTO
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/realestates")
class RealEstateControllerImp(
    val realEStateService: RealEstateService,
    val reservationService: RealEstateService,
    val storageService: StorageService
) : RealEstateController {


    override fun createNewRealEstate(newRealEstateDTO: NewRealEstateDTO): ResponseEntity<Any> {
        return realEStateService.createNewRealEstate(newRealEstateDTO).toResponseEntity()
    }

    override fun createNewReservation(realEstateId: Long, availableReservationTimeDTO: AvailableReservationTimeDTO): ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun deleteRealEstate(realEstateId: Long): ResponseEntity<Any> {
        return realEStateService.deleteRealEstate(realEstateId).toResponseEntity()
    }

    override fun deleteReservation(realEstateId: Long, reservationId: Long): ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun getAllRealEstates(search: String?, page: Int, offset: Int): ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun getAllReservation(realEstateId: Long): ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun getRealEstateById(realEstateId: Long): ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun getReservationDetails(realEstateId: Long, reservationId: Long): ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun newReservation(realEstateId: Long, reservationId: Long, newReservationDTO: NewReservationDTO): ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun updateRealEstate(realEstateId: Long, newRealEstateDTO: NewRealEstateDTO): ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun updateReservation(realEstateId: Long, reservationId: Long, availableReservationTimeDTO: AvailableReservationTimeDTO): ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun uploadFiles(realEstateId: Long, file: MultipartFile): ResponseEntity<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRealEstatesByUserId(page: Int, offset: Int): ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
