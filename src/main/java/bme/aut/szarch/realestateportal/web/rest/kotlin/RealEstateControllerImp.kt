package bme.aut.szarch.realestateportal.web.rest.kotlin


import bme.aut.szarch.realestateportal.domain.kotlin.RealEstateEntity
import bme.aut.szarch.realestateportal.service.kotlin.RealEstateService
import bme.aut.szarch.realestateportal.service.kotlin.ReservationService
import bme.aut.szarch.realestateportal.service.kotlin.StorageServiceImp
import bme.aut.szarch.realestateportal.service.kotlin.dto.*
import org.springframework.core.io.Resource
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
open class RealEstateControllerImp(
    private val realEstateService: RealEstateService,
    private val reservationService: ReservationService,
    private val storageService: StorageServiceImp
) : RealEstateController {
    override fun getAllRealEstates(page: Int?, offset: Int?, specs: Specification<RealEstateEntity>): ResponseEntity<Page<RealEstateDTO>> {
        return realEstateService.getAllRealEstates(specs, PageRequest.of(page ?: 0, offset ?: 1))
    }

    override fun createNewRealEstate(newRealEstate: NewRealEstateDTO): ResponseEntity<Void> {
        return realEstateService.createNewRealEstate(newRealEstate)
    }

    override fun updateRealEstate(realEstateId: Long, newRealEstate: NewRealEstateDTO): ResponseEntity<Void> {
        return realEstateService.updateRealEstate(realEstateId, newRealEstate)
    }

    override fun deleteRealEstate(realEstateId: Long): ResponseEntity<Void> {
        return realEstateService.deleteRealEstate(realEstateId)
    }

    override fun getRealEstatesByUserId(page: Int?, offset: Int?): ResponseEntity<Page<RealEstateDTO>> {
        return realEstateService.getRealEstatesByUserId(PageRequest.of(page ?: 0, offset ?: 1))
    }

    override fun getRealEstateById(realEstateId: Long): ResponseEntity<RealEstateDetailsDTO> {
        return realEstateService.getRealEstateById(realEstateId)
    }

    override fun creatNewAvailableReservationTime(realEstateId: Long, availableReservationTime: AvailableReservationTimeDTO): ResponseEntity<Void> {
        return reservationService.createNewAvailableReservationTime(realEstateId, availableReservationTime)
    }

    override fun deleteReservation(realEstateId: Long, reservationId: Long): ResponseEntity<Void> {
        return reservationService.deleteReservation(realEstateId, reservationId)
    }

    override fun getAllReservation(realEstateId: Long): ResponseEntity<List<ReservationDTO>> {
        return reservationService.getAllReservation(realEstateId)
    }

    override fun getReservationDetails(realEstateId: Long, reservationId: Long): ResponseEntity<ReservationDetailsDTO> {
        return reservationService.getReservationDetails(realEstateId, reservationId)
    }

    override fun makeNewReservation(realEstateId: Long, reservationId: Long, newReservation: NewReservationDTO): ResponseEntity<Void> {
        return reservationService.makeNewReservation(realEstateId, reservationId, newReservation)
    }

    override fun updateReservation(realEstateId: Long, reservationId: Long, availableReservationTime: AvailableReservationTimeDTO): ResponseEntity<Void> {
        return reservationService.updateReservation(realEstateId, reservationId, availableReservationTime)
    }

    override fun uploadFiles(realEstateId: Long, file: MultipartFile): ResponseEntity<Void> {
        return storageService.uploadFiles(realEstateId, file)
    }

    override fun downloadFile(fileName: String): ResponseEntity<Resource> {
        return storageService.loadFile(fileName)
    }
}
