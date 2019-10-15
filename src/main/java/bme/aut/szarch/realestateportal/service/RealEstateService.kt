package bme.aut.szarch.realestateportal.service

import bme.aut.szarch.realestateportal.repository.RealEstateRepository
import bme.aut.szarch.realestateportal.repository.ReservationRepository
import bme.aut.szarch.realestateportal.service.DataTransferResult.ClientFailure
import bme.aut.szarch.realestateportal.service.DataTransferResult.ClientFailureReason.NOT_FOUND
import bme.aut.szarch.realestateportal.service.DataTransferResult.Success
import bme.aut.szarch.realestateportal.service.DataTransferResult.SuccessType.CREATED
import bme.aut.szarch.realestateportal.service.DataTransferResult.SuccessType.OK
import bme.aut.szarch.realestateportal.service.dto.*
import bme.aut.szarch.realestateportal.service.mapper.toRealEstateEntity
import bme.aut.szarch.realestateportal.service.mapper.toReservationEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
open class RealEstateService(
    private val realEstateRepository: RealEstateRepository,
    private val reservationRepository: ReservationRepository,
    private val userService: UserService
) {
    fun createNewRealEstate(newRealEstate: NewRealEstate): ResponseEntity<Void> {
        val currentUserIsPresent = userService.userWithAuthorities.isPresent

        if (!currentUserIsPresent) {
            return ResponseEntity.status(401).build<Void>()
        }

        val currentUser = userService.userWithAuthorities.get()
        realEstateRepository.save(newRealEstate.toRealEstateEntity(currentUser))
        return ResponseEntity.status(201).build()
    }

    fun createNewReservation(realEstateId: Long, availableReservationTime: AvailableReservationTime): ResponseEntity<Void> {
        val relatedRealestate = realEstateRepository.getOne(realEstateId)
        reservationRepository.save(availableReservationTime.toReservationEntity(relatedRealestate))
        return ResponseEntity.status(201).build()
    }

    fun deleteRealEstate(realEstateId: Long): ResponseEntity<Void> {
        val currentUser = userService.userWithAuthorities.get()
        val realEstate = realEstateRepository.getOne(realEstateId)

        if (currentUser.id != realEstate.user.id) {
            return ResponseEntity.status(401).build()
        }

        realEstateRepository.delete(realEstate)
        return ResponseEntity.ok().build()
    }

    fun deleteReservation(realEstateId: Long, reservationId: Long): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    fun getAllRealEstates(search: String, page: Int?, offset: Int?): ResponseEntity<List<RealEstate>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    fun getAllReservation(realEstateId: Long): ResponseEntity<List<Reservation>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    fun getRealEstateById(realEstateId: Long): ResponseEntity<RealEstateDetails> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    fun getReservationDetails(realEstateId: Long, reservationId: Long): ResponseEntity<ReservationDetails> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    fun newReservation(realEstateId: Long, reservationId: Long, newReservation: NewReservation): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    fun updateRealEstate(realEstateId: Long, newRealEstate: NewRealEstate): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    fun updateReservation(realEstateId: Long, reservationId: Long, availableReservationTime: AvailableReservationTime): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    fun uploadFiles(realEstateId: Long): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    fun getRealEstatesByUserId(page: Int, offset: Int): ResponseEntity<List<RealEstate>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}

sealed class DataTransferResult<out T : Any> {
    data class Success<out T : Any>(val successType: SuccessType, val result: T) : DataTransferResult<T>()
    data class ClientFailure(val failReason: ClientFailureReason, val message: String) : DataTransferResult<Nothing>()
    data class ServerFailure(val failReason: ServerFailureReason, val message: String) : DataTransferResult<Nothing>()

    enum class SuccessType {
        OK,
        CREATED
    }

    enum class ClientFailureReason {
        NOT_FOUND,
        NOT_AUTHORIZE,
        UNAUTHORIZED,
        BAD_REQUEST,
        FORBIDDEN
    }

    enum class ServerFailureReason {
        INTERNAL_SERVER_ERROR,
    }
}

fun <T : Any> DataTransferResult<T>.toResponseEntity(): ResponseEntity<T> {
    return when (this) {
        is Success -> when (this.successType) {
            OK -> ResponseEntity.ok(this.result)
            CREATED -> ResponseEntity.status(201).build()
        }
        is ClientFailure -> when (this.failReason) {
            NOT_FOUND -> ResponseEntity.status(404).body().build()
        }
    }
}


