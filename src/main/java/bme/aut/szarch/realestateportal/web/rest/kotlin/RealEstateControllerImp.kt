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
import bme.aut.szarch.realestateportal.service.kotlin.util.result.StorageMethodResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.StorageMethodResult.*
import bme.aut.szarch.realestateportal.service.kotlin.util.result.toResponseEntity
import org.springframework.core.io.Resource
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.regex.Pattern

@RestController
@RequestMapping("/realestates")
open class RealEstateControllerImp(
    private val realEStateService: RealEstateService,
    private val reservationService: ReservationService,
    private val storageService: StorageServiceImp
) : RealEstateController {
    override fun createNewRealEstate(newRealEstateDTO: NewRealEstateDTO): ResponseEntity<Any> {
        return realEStateService.createNewRealEstate(newRealEstateDTO).toResponseEntity()
    }

    override fun updateRealEstate(realEstateId: Long, newRealEstateDTO: NewRealEstateDTO): ResponseEntity<Any> {
        return realEStateService.updateRealEstate(realEstateId, newRealEstateDTO).toResponseEntity()
    }

    override fun deleteRealEstate(realEstateId: Long): ResponseEntity<Any> {
        return realEStateService.deleteRealEstate(realEstateId).toResponseEntity()
    }

    override fun getAllRealEstates(search: String?, page: Int, offset: Int): ResponseEntity<Any> {
        val builder = RealEstateSpecificationBuilder()
        val pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),")
        val matcher = pattern.matcher("$search,")
        while (matcher.find()) {
            val searchCriteria = SpecSearchCriteria(matcher.group(1), getSimpleOperation(matcher.group(2)[0]), matcher.group(3))
            builder.with(searchCriteria)
        }
        return realEStateService.getAllRealEstates(builder.build(), PageRequest.of(page, offset)).toResponseEntity()
    }

    override fun getRealEstatesByUserId(page: Int, offset: Int): ResponseEntity<Any> {
        return realEStateService.getRealEstatesByUserId(PageRequest.of(page, offset)).toResponseEntity()
    }

    override fun getRealEstateById(realEstateId: Long): ResponseEntity<Any> {
        return realEStateService.getRealEstateById(realEstateId).toResponseEntity()
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
        val result = storageService.uploadFiles(realEstateId, file)
        return when (result) {
            is Success -> ResponseEntity.ok().build()
            is Failed -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result.message)
            else -> ResponseEntity.ok().build()
        }
    }

    override fun downloadFile(filename: String): ResponseEntity<Any> {
        val result: StorageMethodResult<Resource> = storageService.loadFile(filename)
        return when (result) {
            is SuccessWithResult -> {
                val fileResource = result.result
                ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResource.filename + "\"")
                    .body(fileResource)
            }
            is Failed -> {
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(result.message)
            }
            is Success -> {
                ResponseEntity.ok().build()
            }
        }
    }
}
