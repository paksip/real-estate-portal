package bme.aut.szarch.realestateportal.service.kotlin

import bme.aut.szarch.realestateportal.repository.kotlin.RealEstateRepository
import bme.aut.szarch.realestateportal.repository.kotlin.ReservationRepository
import bme.aut.szarch.realestateportal.service.UserService
import bme.aut.szarch.realestateportal.service.kotlin.dto.AvailableReservationTimeDTO
import bme.aut.szarch.realestateportal.service.kotlin.dto.NewReservationDTO
import bme.aut.szarch.realestateportal.service.kotlin.dto.ReservationDTO
import bme.aut.szarch.realestateportal.service.kotlin.dto.ReservationDetailsDTO
import bme.aut.szarch.realestateportal.service.kotlin.mapper.toReservationEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Failure
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Success
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
open class ReservationService(
    private val realEstateRepository: RealEstateRepository,
    private val reservationRepository: ReservationRepository,
    private val userService: UserService
) {


    fun createNewReservation(realEstateId: Long, availableReservationTimeDTO: AvailableReservationTimeDTO): DataTransferResult<Void> {
        if (realEstateRepository.findById(realEstateId).isPresent) {
            realEstateRepository.findById(realEstateId).get().let {
                reservationRepository.save(availableReservationTimeDTO.toReservationEntity(it))
                return Success(HttpStatus.CREATED)
            }
        } else {
            return Failure(HttpStatus.NOT_FOUND, "Does not exists any recourse with id : $realEstateId")
        }
    }


    fun deleteReservation(realEstateId: Long, reservationId: Long): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    fun getAllReservation(realEstateId: Long): ResponseEntity<List<ReservationDTO>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    fun getReservationDetails(realEstateId: Long, reservationId: Long): ResponseEntity<ReservationDetailsDTO> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    fun newReservation(realEstateId: Long, reservationId: Long, newReservationDTO: NewReservationDTO): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    fun updateReservation(realEstateId: Long, reservationId: Long, availableReservationTimeDTO: AvailableReservationTimeDTO): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
