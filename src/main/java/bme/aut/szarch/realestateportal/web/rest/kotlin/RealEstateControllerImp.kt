package bme.aut.szarch.realestateportal.web.rest.kotlin

import bme.aut.szarch.realestateportal.repository.kotlin.realstatespecification.RealEstateSpecificationBuilder
import bme.aut.szarch.realestateportal.repository.kotlin.realstatespecification.SearchOperation.getSimpleOperation
import bme.aut.szarch.realestateportal.repository.kotlin.realstatespecification.SpecSearchCriteria
import bme.aut.szarch.realestateportal.service.kotlin.RealEstateService
import bme.aut.szarch.realestateportal.service.kotlin.ReservationService
import bme.aut.szarch.realestateportal.service.kotlin.StorageServiceImp
import bme.aut.szarch.realestateportal.service.kotlin.dto.AvailableReservationTimeDTO
import bme.aut.szarch.realestateportal.service.kotlin.dto.NewRealEstateDTO
import bme.aut.szarch.realestateportal.service.kotlin.dto.NewReservationDTO
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
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
    override fun createNewRealEstate(newRealEstateDTO: NewRealEstateDTO): ResponseEntity<Any> {
        return realEstateService.createNewRealEstate(newRealEstateDTO).toResponseEntity()
    }

    override fun updateRealEstate(realEstateId: Long, newRealEstateDTO: NewRealEstateDTO): ResponseEntity<Any> {
        return realEstateService.updateRealEstate(realEstateId, newRealEstateDTO).toResponseEntity()
    }

    override fun deleteRealEstate(realEstateId: Long): ResponseEntity<Any> {
        return realEstateService.deleteRealEstate(realEstateId).toResponseEntity()
    }

    override fun getAllRealEstates(search: String?, page: Int?, offset: Int?): ResponseEntity<Any> {
        val builder = RealEstateSpecificationBuilder()
        val pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),")
        val matcher = pattern.matcher("$search,")
        while (matcher.find()) {
            val searchCriteria = SpecSearchCriteria(matcher.group(1), getSimpleOperation(matcher.group(2)[0]), matcher.group(3))
            builder.with(searchCriteria)
        }
        return realEstateService.getAllRealEstates(builder.build(), PageRequest.of(page?:0, offset?:1)).toResponseEntity()
    }

    override fun getRealEstatesByUserId(page: Int, offset: Int): ResponseEntity<Any> {
        return realEstateService.getRealEstatesByUserId(PageRequest.of(page, offset)).toResponseEntity()
    }

    override fun getRealEstateById(realEstateId: Long): ResponseEntity<Any> {
        return realEstateService.getRealEstateById(realEstateId).toResponseEntity()
    }

    override fun creatAvailReservetionTime(realEstateId: Long, availableReservationTimeDTO: AvailableReservationTimeDTO): ResponseEntity<Any> {
        return reservationService.createNewAvailableReservationTime(realEstateId, availableReservationTimeDTO).toResponseEntity()
    }

    override fun deleteReservation(realEstateId: Long, reservationId: Long): ResponseEntity<Any> {
        return reservationService.deleteReservation(realEstateId, reservationId).toResponseEntity()
    }

    override fun getAllReservation(realEstateId: Long): ResponseEntity<Any> {
        return reservationService.getAllReservation(realEstateId).toResponseEntity()
    }

    override fun getReservationDetails(realEstateId: Long, reservationId: Long): ResponseEntity<Any> {
        return reservationService.getReservationDetails(realEstateId, reservationId).toResponseEntity()
    }

    override fun makeNewReservation(realEstateId: Long, reservationId: Long, newReservationDTO: NewReservationDTO): ResponseEntity<Any> {
        return reservationService.makeNewReservation(realEstateId, reservationId, newReservationDTO).toResponseEntity()
    }


    override fun updateReservation(realEstateId: Long, reservationId: Long, availableReservationTimeDTO: AvailableReservationTimeDTO): ResponseEntity<Any> {
        return reservationService.updateReservation(realEstateId, reservationId, availableReservationTimeDTO).toResponseEntity()
    }

    override fun uploadFiles(realEstateId: Long, file: MultipartFile): ResponseEntity<Any> {
        return storageService.uploadFiles(realEstateId, file).toResponseEntity()
    }

    override fun downloadFile(filename: String): ResponseEntity<Any> {
        return storageService.loadFile(filename).toResponseEntity()
    }
}
