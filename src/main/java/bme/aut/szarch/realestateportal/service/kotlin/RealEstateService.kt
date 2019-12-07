package bme.aut.szarch.realestateportal.service.kotlin

import bme.aut.szarch.realestateportal.repository.kotlin.RealEstateRepository
import bme.aut.szarch.realestateportal.service.MockUserService
import bme.aut.szarch.realestateportal.service.kotlin.dto.NewRealEstateDTO
import bme.aut.szarch.realestateportal.service.kotlin.extensions.orNull
import bme.aut.szarch.realestateportal.service.kotlin.mapper.*
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.create.executeCreateWithParent
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.read.executeRead
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.read.executeReadListWithAuthorization
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.update.executeUpdateOrDeleteWithAuthorization
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Success
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service


@Service
open class RealEstateService(
    private val realEstateRepository: RealEstateRepository,
    private val userService: MockUserService,
    private val storageService: StorageServiceImp
) {
    open fun getRealEstateById(realEstateId: Long) = executeRead(
        getTargetEntity = { realEstateRepository.findById(realEstateId).orNull() },
        mappingCall = { realEstateEntity ->
            realEstateRepository.save(realEstateEntity.incrementSpectatorsCount())
            realEstateEntity.toRealEstateDetailsDTO(getFilenamesByRealEstateId(realEstateId))
        },
        onSuccess = { realEstaDto -> Success(HttpStatus.OK, realEstaDto) }
    )

    open fun getAllRealEstates(pageable: Pageable) = executeRead(
        getTargetEntity = { realEstateRepository.findAll(pageable) },
        mappingCall = { page -> page.map { it.toRealEstateDTO(getFilenamesByRealEstateId(it.id)) } },
        onSuccess = { realEstateDto -> Success(HttpStatus.OK, realEstateDto) }
    )

    open fun getRealEstatesByUserId(pageable: Pageable) = executeReadListWithAuthorization(
        getUserCall = { userService.userWithAuthorities().orNull() },
        getEntityListCall = { userId -> realEstateRepository.findByUserId(userId, pageable) },
        mappingCall = { realEstates -> realEstates.map { it.toRealEstateDTO(getFilenamesByRealEstateId(it.id)) } },
        onSuccess = { realEstates -> Success(HttpStatus.OK, realEstates) }
    )

    open fun createNewRealEstate(newRealEstateDTO: NewRealEstateDTO) = executeCreateWithParent(
        getParentEntityCall = { userService.userWithAuthorities().orNull() },
        operationCall = { user -> realEstateRepository.save(newRealEstateDTO.toRealEstateEntity(user)) }
    )

    open fun updateRealEstate(realEstateId: Long, newRealEstateDTO: NewRealEstateDTO) = executeUpdateOrDeleteWithAuthorization(
        getUserCall = { userService.userWithAuthorities().orNull() },
        getEntityCall = { realEstateRepository.findById(realEstateId).orNull() },
        operationCall = { realEstate -> realEstateRepository.save(realEstate.toUpdatedRealEstateEntity(newRealEstateDTO)) }
    )


    open fun deleteRealEstate(realEstateId: Long) = executeUpdateOrDeleteWithAuthorization(
        getUserCall = { userService.userWithAuthorities().orNull() },
        getEntityCall = { realEstateRepository.findById(realEstateId).orNull() },
        operationCall = { realEstate -> realEstateRepository.delete(realEstate) }
    )

    private fun getFilenamesByRealEstateId(realEstateId: Long): List<String> =
        storageService.loadFiles(realEstateId)
}


