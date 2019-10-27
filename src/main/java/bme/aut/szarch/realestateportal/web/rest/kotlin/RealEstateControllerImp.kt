package bme.aut.szarch.realestateportal.web.rest.kotlin

import bme.aut.szarch.realestateportal.repository.kotlin.realstatespecification.RealEstateSpecificationBuilder
import bme.aut.szarch.realestateportal.repository.kotlin.realstatespecification.SearchOperation.getSimpleOperation
import bme.aut.szarch.realestateportal.repository.kotlin.realstatespecification.SpecSearchCriteria
import bme.aut.szarch.realestateportal.service.kotlin.RealEstateService
import bme.aut.szarch.realestateportal.service.kotlin.ReservationService
import bme.aut.szarch.realestateportal.service.kotlin.StorageServiceImp
import bme.aut.szarch.realestateportal.service.kotlin.dto.*
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import org.springframework.core.io.Resource
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.util.regex.Pattern

@Component
open class RealEstateControllerImp(
    private val realEstateService: RealEstateService,
    private val reservationService: ReservationService,
    private val storageService: StorageServiceImp
) : RealEstateController {
    override fun createNewRealEstate(newRealEstate: NewRealEstateDTO): ResponseEntity<Void> {
        return realEstateService.createNewRealEstate(newRealEstate).toResponseEntity()
    }

    override fun updateRealEstate(realEstateId: Long, newRealEstate: NewRealEstateDTO): ResponseEntity<Void> {
        return realEstateService.updateRealEstate(realEstateId, newRealEstate).toResponseEntity()
    }

    override fun deleteRealEstate(realEstateId: Long): ResponseEntity<Void> {
        return realEstateService.deleteRealEstate(realEstateId).toResponseEntity()
    }

    override fun getAllRealEstates(page: Int?, offset: Int?, search: String?): ResponseEntity<Page<RealEstateDTO>> {
        val builder = RealEstateSpecificationBuilder()
        val pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),")
        val matcher = pattern.matcher("$search,")
        while (matcher.find()) {
            val searchCriteria = SpecSearchCriteria(matcher.group(1), getSimpleOperation(matcher.group(2)[0]), matcher.group(3))
            builder.with(searchCriteria)
        }
        return realEstateService.getAllRealEstates(builder.build(), PageRequest.of(page ?: 0, offset
            ?: 1)).toResponseEntity()
    }

    override fun getRealEstatesByUserId(page: Int?, offset: Int?): ResponseEntity<Page<RealEstateDTO>> {
        return realEstateService.getRealEstatesByUserId(PageRequest.of(page ?: 0, offset ?: 1)).toResponseEntity()
    }

    override fun getRealEstateById(realEstateId: Long): ResponseEntity<RealEstateDetailsDTO> {
        return realEstateService.getRealEstateById(realEstateId).toResponseEntity()
    }

    override fun creatNewAvailableReservetionTime(realEstateId: Long, availableReservationTime: AvailableReservationTimeDTO): ResponseEntity<Void> {
        return reservationService.createNewAvailableReservationTime(realEstateId, availableReservationTime).toResponseEntity()
    }

    override fun deleteReservation(realEstateId: Long, reservationId: Long): ResponseEntity<Void> {
        return reservationService.deleteReservation(realEstateId, reservationId).toResponseEntity()
    }

    override fun getAllReservation(realEstateId: Long): ResponseEntity<List<ReservationDTO>> {
        return reservationService.getAllReservation(realEstateId).toResponseEntity()
    }

    override fun getReservationDetails(realEstateId: Long, reservationId: Long): ResponseEntity<ReservationDetailsDTO> {
        return reservationService.getReservationDetails(realEstateId, reservationId).toResponseEntity()
    }

    override fun makeNewReservation(realEstateId: Long, reservationId: Long, newReservation: NewReservationDTO): ResponseEntity<Void> {
        return reservationService.makeNewReservation(realEstateId, reservationId, newReservation).toResponseEntity()
    }

    override fun updateReservation(realEstateId: Long, reservationId: Long, availableReservationTime: AvailableReservationTimeDTO): ResponseEntity<Void> {
        return reservationService.updateReservation(realEstateId, reservationId, availableReservationTime).toResponseEntity()
    }

    override fun uploadFiles(realEstateId: Long, file: MultipartFile): ResponseEntity<Void> {
        return storageService.uploadFiles(realEstateId, file).toResponseEntity()
    }

    override fun downloadFile(fileName: String): ResponseEntity<Resource> {
        return storageService.loadFile(fileName).toResponseEntity()
    }
}
