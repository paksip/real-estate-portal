package bme.aut.szarch.realestateportal.service.kotlin

import bme.aut.szarch.realestateportal.domain.kotlin.RealEstateEntity
import bme.aut.szarch.realestateportal.repository.kotlin.RealEstateRepository
import bme.aut.szarch.realestateportal.service.UserService
import bme.aut.szarch.realestateportal.service.kotlin.dto.NewRealEstateDTO
import bme.aut.szarch.realestateportal.service.kotlin.extensions.orNull
import bme.aut.szarch.realestateportal.service.kotlin.mapper.*
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.create.executeCreateWithParent
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.create.executeRead
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.create.executeReadListWithAuthorization
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.create.executeUpdateOrDeleteWithAuthorization
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Success
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
open class RealEstateService(
    private val realEstateRepository: RealEstateRepository,
    private val userService: UserService,
    private val storageService: StorageServiceImp
) {
    @Transactional(readOnly = true)
    open fun getRealEstateById(realEstateId: Long) = executeRead(
        getTargetEntity = { realEstateRepository.findById(realEstateId).orNull() },
        mappingCall = { realEstateEntity ->
            realEstateRepository.save(realEstateEntity.incrementSpectatorsCount())
            realEstateEntity.toRealEstateDetailsDTO(getFilenamesByRealEstateId(realEstateId))
        },
        onSuccess = { realEstaDto -> Success(HttpStatus.OK, realEstaDto) }
    )

    @Transactional(readOnly = true)
    open fun getAllRealEstates(realEstateSpecification: Specification<RealEstateEntity>, pageable: Pageable) = executeRead(
        getTargetEntity = { realEstateRepository.findAll(realEstateSpecification, pageable) },
        mappingCall = { page -> page.map { it.toRealEstateDTO(getFilenamesByRealEstateId(it.id)) } },
        onSuccess = { realEstateDto -> Success(HttpStatus.OK, realEstateDto) }
    )

    @Transactional(readOnly = true)
    open fun getRealEstatesByUserId(pageable: Pageable) = executeReadListWithAuthorization(
        getUserCall = { userService.userWithAuthorities.orNull() },
        getEntityListCall = { userId -> realEstateRepository.findByUserId(userId, pageable) },
        mappingCall = { realEstates -> realEstates.map { it.toRealEstateDTO(getFilenamesByRealEstateId(it.id)) } },
        onSuccess = { realEstates -> Success(HttpStatus.OK, realEstates) }
    )

    fun createNewRealEstate(newRealEstateDTO: NewRealEstateDTO) = executeCreateWithParent(
        getParentEntityCall = { userService.userWithAuthorities.orNull() },
        operationCall = { user -> realEstateRepository.save(newRealEstateDTO.toRealEstateEntity(user)) }
    )

    fun updateRealEstate(realEstateId: Long, newRealEstateDTO: NewRealEstateDTO) = executeUpdateOrDeleteWithAuthorization(
        getUserCall = { userService.userWithAuthorities.orNull() },
        getEntityCall = { realEstateRepository.findById(realEstateId).orNull() },
        operationCall = { realEstate -> realEstateRepository.save(realEstate.toUpdatedRealEstateEntity(newRealEstateDTO)) }
    )


    fun deleteRealEstate(realEstateId: Long) = executeUpdateOrDeleteWithAuthorization(
        getUserCall = { userService.userWithAuthorities.orNull() },
        getEntityCall = { realEstateRepository.findById(realEstateId).orNull() },
        operationCall = { realEstate -> realEstateRepository.delete(realEstate) }
    )


    private fun getFilenamesByRealEstateId(realEstateId: Long): List<String> =
        storageService.loadFiles(realEstateId)
}


