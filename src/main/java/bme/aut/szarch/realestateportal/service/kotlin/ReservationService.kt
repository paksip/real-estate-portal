package bme.aut.szarch.realestateportal.service.kotlin

import bme.aut.szarch.realestateportal.repository.kotlin.RealEstateRepository
import bme.aut.szarch.realestateportal.repository.kotlin.ReservationRepository
import bme.aut.szarch.realestateportal.service.UserService
import bme.aut.szarch.realestateportal.service.kotlin.dto.AvailableReservationTimeDTO
import bme.aut.szarch.realestateportal.service.kotlin.dto.NewReservationDTO
import bme.aut.szarch.realestateportal.service.kotlin.extensions.orNull
import bme.aut.szarch.realestateportal.service.kotlin.mapper.*
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.create.executeCreateWithAutParent
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.read.executeRead
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.read.executeReadWithAuthParent
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.update.executeUpdateOrDelete
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.update.executeUpdateOrDeleteWithAuthParent
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Success
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
open class ReservationService(
    private val realEstateRepository: RealEstateRepository,
    private val reservationRepository: ReservationRepository,
    private val userService: UserService
) {
    open fun createNewAvailableReservationTime(
        realEstateId: Long,
        availableReservationTimeDTO: AvailableReservationTimeDTO
    ) = executeCreateWithAutParent(
        getUserCall = {
        userService.userWithAuthorities.orNull()
    },
        getParentEntityCall = { realEstateRepository.findById(realEstateId).orNull() },
        operationCall = {
            realEstate -> reservationRepository.save(availableReservationTimeDTO.toFreeReservationEntity(realEstate)) }
    )

    open fun deleteReservation(realEstateId: Long, reservationId: Long) = executeUpdateOrDeleteWithAuthParent(
        getUserCall = { userService.userWithAuthorities.orNull() },
        getUserRelatedParentEntityCalls = listOf { realEstateRepository.findById(realEstateId).orNull() },
        getTargetEntityCall = { reservationRepository.findByIdAndRealEstateId(reservationId, realEstateId) },
        operationCall = { reservation -> reservationRepository.delete(reservation) },
        validationCall = { reservation -> check(reservation.isFree) { "The reservation is not free!" } }
    )

    open fun getAllReservation(realEstateId: Long) = executeRead(
        getTargetEntity = { reservationRepository.findByRealEstateId(realEstateId) },
        mappingCall = { realEstates -> realEstates.map { it.toReservationDTO() } },
        onSuccess = { realEstatesDTO -> Success(HttpStatus.OK, realEstatesDTO) }
    )

    open fun getReservationDetails(realEstateId: Long, reservationId: Long) = executeReadWithAuthParent(
        getUserCall = { userService.userWithAuthorities.orNull() },
        getUserRelatedParentEntityCalls = listOf { realEstateRepository.findById(realEstateId).orNull() },
        getTargetEntity = { reservationRepository.findByIdAndRealEstateId(reservationId, realEstateId) },
        validationCall = { reservation -> check(reservation.isFree.not()) { "The reservation is free! Thus that does not contain any Details" } },
        mappingCall = { reservation -> reservation.toReservationDetailsDTO() },
        onSuccess = { reservationDTO -> Success(HttpStatus.OK, reservationDTO) }
    )

    open fun makeNewReservation(realEstateId: Long, reservationId: Long, newReservationDTO: NewReservationDTO) = executeUpdateOrDelete(
        databaseGetCall = { reservationRepository.findByIdAndRealEstateId(reservationId, realEstateId) },
        validationCall = { reservation -> check(reservation.isFree) { "The reservation is not free!" } },
        operationCall = { reservation -> reservationRepository.save(reservation.toReservedReservationEntity(newReservationDTO)) }
    )

    open fun updateReservation(realEstateId: Long, reservationId: Long, availableReservationTimeDTO: AvailableReservationTimeDTO) = executeUpdateOrDeleteWithAuthParent(
        getUserCall = { userService.userWithAuthorities.orNull() },
        getUserRelatedParentEntityCalls = listOf { realEstateRepository.findById(realEstateId).orNull() },
        getTargetEntityCall = { reservationRepository.findByIdAndRealEstateId(reservationId, realEstateId) },
        operationCall = { reservationRepository.save(it.toUpdatedFreeReservationEntity(availableReservationTimeDTO)) },
        validationCall = { reservation -> check(reservation.isFree) { "The reservation is not free!" } }
    )
}
