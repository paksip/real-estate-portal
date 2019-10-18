package bme.aut.szarch.realestateportal.service.kotlin

import bme.aut.szarch.realestateportal.repository.kotlin.RealEstateRepository
import bme.aut.szarch.realestateportal.repository.kotlin.ReservationRepository
import bme.aut.szarch.realestateportal.security.SecurityUtils
import bme.aut.szarch.realestateportal.service.UserService
import bme.aut.szarch.realestateportal.service.kotlin.dto.AvailableReservationTimeDTO
import bme.aut.szarch.realestateportal.service.kotlin.dto.NewReservationDTO
import bme.aut.szarch.realestateportal.service.kotlin.dto.ReservationDTO
import bme.aut.szarch.realestateportal.service.kotlin.dto.ReservationDetailsDTO
import bme.aut.szarch.realestateportal.service.kotlin.extensions.orNull
import bme.aut.szarch.realestateportal.service.kotlin.mapper.toReservationDTO
import bme.aut.szarch.realestateportal.service.kotlin.mapper.toReservationDetailsDTO
import bme.aut.szarch.realestateportal.service.kotlin.mapper.toReservationEntity
import bme.aut.szarch.realestateportal.service.kotlin.mapper.toUpdatedReservationEntity
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


    fun createNewAvailableReservationTime(realEstateId: Long, availableReservationTimeDTO: AvailableReservationTimeDTO): DataTransferResult<Void> {
        val realEstate = realEstateRepository
            .findById(realEstateId)
            .orNull() ?: return Failure(HttpStatus.NOT_FOUND, "Does not exists any recourse with id : $realEstateId")

        val user = userService
            .userWithAuthorities
            .orNull() ?: return Failure(HttpStatus.UNAUTHORIZED, "User not Authenticated")

        if (user.id != realEstate.user.id) {
            return Failure(HttpStatus.UNAUTHORIZED, "User not Authorized")
        }

        reservationRepository.save(availableReservationTimeDTO.toReservationEntity(realEstate))
        return Success(HttpStatus.CREATED)
    }


    fun deleteReservation(realEstateId: Long, reservationId: Long): DataTransferResult<Void> {
        val realEstate = realEstateRepository
            .findById(realEstateId)
            .orNull() ?: return Failure(HttpStatus.NOT_FOUND, "Does not exists any recourse with id : $realEstateId")

        val reservation = reservationRepository
            .findByIdAndRealEstateId(reservationId, realEstateId)
            ?: return Failure(HttpStatus.NOT_FOUND, "Does not exists any recourse with id : $reservationId")

        val user = userService
            .userWithAuthorities
            .orNull() ?: return Failure(HttpStatus.UNAUTHORIZED, "User not Authenticated")

        if (user.id != realEstate.user.id) {
            return Failure(HttpStatus.UNAUTHORIZED, "User not Authorized")
        }

        reservationRepository.delete(reservation)
        return Success(HttpStatus.OK)
    }

    fun getAllReservation(realEstateId: Long): DataTransferResult<List<ReservationDTO>> {
        val user = userService
            .userWithAuthorities
            .orNull()

        val realEstate = realEstateRepository
            .findById(realEstateId)
            .orNull() ?: return Failure(HttpStatus.NOT_FOUND, "Does not exists any recourse with id : $realEstateId")

        //TODO findByRealEstateId megnézni h ez müködik!
        val reservations = reservationRepository
            .findByRealEstateId(realEstateId).filter { it.isFree }


        if (user != null && user.id == realEstate.user.id) {
            return Success(HttpStatus.OK, reservations.map { it.toReservationDTO() })
        }

        return Success(HttpStatus.OK, reservations.filter { it.isFree }.map { it.toReservationDTO() })
    }

    fun getReservationDetails(realEstateId: Long, reservationId: Long): DataTransferResult<ReservationDetailsDTO> {
        val user = userService
            .userWithAuthorities
            .orNull() ?: return Failure(HttpStatus.UNAUTHORIZED, "User not Authenticated")

        val realEstate = realEstateRepository
            .findById(realEstateId)
            .orNull() ?: return Failure(HttpStatus.NOT_FOUND, "Does not exists any recourse with id : $realEstateId")

        val reservation = reservationRepository
            .findByIdAndRealEstateId(reservationId, realEstateId)
            ?: return Failure(HttpStatus.NOT_FOUND, "Does not exists any recourse with id : $reservationId")

        if (user.id != realEstate.user.id) {
            return Failure(HttpStatus.UNAUTHORIZED, "User not Authorized")
        }

        return Success(HttpStatus.OK, reservation.toReservationDetailsDTO())
    }

    fun makeNewReservation(realEstateId: Long, reservationId: Long, newReservationDTO: NewReservationDTO): DataTransferResult<Void> {
        val reservation = reservationRepository
            .findByIdAndRealEstateId(reservationId, realEstateId)
            ?: return Failure(HttpStatus.NOT_FOUND, "Does not exists any recourse with id : $reservationId")


        val newReservation = reservation.copy(
            emailAddress = newReservationDTO.email,
            phoneNumber = newReservationDTO.phoneNumber,
            message = newReservationDTO.message,
            userName = newReservationDTO.userName,
            isFree = false
        )
        reservationRepository.save(newReservation)
        return Success(HttpStatus.CREATED)
    }

    fun updateReservation(realEstateId: Long, reservationId: Long, availableReservationTimeDTO: AvailableReservationTimeDTO): DataTransferResult<Void> {
        val realEstate = realEstateRepository
            .findById(realEstateId)
            .orNull() ?: return Failure(HttpStatus.NOT_FOUND, "Does not exists any recourse with id : $realEstateId")

        val reservation = reservationRepository
            .findByIdAndRealEstateId(reservationId, realEstateId)
            ?: return Failure(HttpStatus.NOT_FOUND, "Does not exists any recourse with id : $reservationId")

        val user = userService
            .userWithAuthorities
            .orNull() ?: return Failure(HttpStatus.UNAUTHORIZED, "User not Authenticated")

        if (user.id != realEstate.user.id) {
            return Failure(HttpStatus.UNAUTHORIZED, "User not Authorized")
        }

        if (!reservation.isFree) {
            return Failure(HttpStatus.FORBIDDEN, "The reservation is not free!")
        }

        val newReservation = reservation.copy(
            from = availableReservationTimeDTO.from,
            to = availableReservationTimeDTO.to
        )
        reservationRepository.save(newReservation)
        return Success(HttpStatus.CREATED)
    }
}
