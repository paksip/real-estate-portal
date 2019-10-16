package bme.aut.szarch.realestateportal.service

import bme.aut.szarch.realestateportal.repository.RealEstateRepository
import bme.aut.szarch.realestateportal.repository.ReservationRepository
import bme.aut.szarch.realestateportal.service.dto.AvailableReservationTime
import bme.aut.szarch.realestateportal.service.dto.NewReservation
import bme.aut.szarch.realestateportal.service.dto.Reservation
import bme.aut.szarch.realestateportal.service.dto.ReservationDetails
import bme.aut.szarch.realestateportal.service.mapper.toReservationEntity
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


    fun createNewReservation(realEstateId: Long, availableReservationTime: AvailableReservationTime): DataTransferResult<Void> {
        if (realEstateRepository.findById(realEstateId).isPresent) {
            realEstateRepository.findById(realEstateId).get().let {
                reservationRepository.save(availableReservationTime.toReservationEntity(it))
                return DataTransferResult.Success(HttpStatus.CREATED)
            }
        } else {
            return DataTransferResult.Failure(HttpStatus.NOT_FOUND, "Does not exists any recourse with id : $realEstateId")
        }
    }


    fun deleteReservation(realEstateId: Long, reservationId: Long): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    fun getAllReservation(realEstateId: Long): ResponseEntity<List<Reservation>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    fun getReservationDetails(realEstateId: Long, reservationId: Long): ResponseEntity<ReservationDetails> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    fun newReservation(realEstateId: Long, reservationId: Long, newReservation: NewReservation): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    fun updateReservation(realEstateId: Long, reservationId: Long, availableReservationTime: AvailableReservationTime): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
