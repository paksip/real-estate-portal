package bme.aut.szarch.realestateportal.service.kotlin

import bme.aut.szarch.realestateportal.domain.kotlin.RealEstateEntity
import bme.aut.szarch.realestateportal.repository.UserRepository
import bme.aut.szarch.realestateportal.repository.kotlin.RealEstateRepository
import bme.aut.szarch.realestateportal.service.MailService
import bme.aut.szarch.realestateportal.service.UserService
import bme.aut.szarch.realestateportal.service.kotlin.dto.NewRealEstateDTO
import bme.aut.szarch.realestateportal.service.kotlin.dto.RealEstateDTO
import bme.aut.szarch.realestateportal.service.kotlin.dto.RealEstateDetailsDTO
import bme.aut.szarch.realestateportal.service.kotlin.extensions.orNull
import bme.aut.szarch.realestateportal.service.kotlin.mapper.*
import bme.aut.szarch.realestateportal.service.kotlin.mapper.toRealEstateDTO
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Error
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Success
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.HttpStatus
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
open class RealEstateService(
    private val realEstateRepository: RealEstateRepository,
    private val userService: UserService,
    private val userRepository: UserRepository,
    private val storageService: StorageServiceImp,
    private val mailService: MailService
) {

    companion object {
        const val WEEK_IN_MILLISEC: Long = 604_800_000
    }

    //TODO incrementt spectators count
    @Transactional(readOnly = true)
    open fun getRealEstateById(realEstateId: Long): DataTransferResult<RealEstateDetailsDTO> {
        val fileNames = getFilenamesByRealEstateId(realEstateId)

        realEstateRepository.findById(realEstateId).orNull()?.let { realEstateEntity ->
            return Success(HttpStatus.OK, realEstateEntity.toRealEstateDetailsDTO(fileNames))
        }

        return Error(HttpStatus.NOT_FOUND, "Resource does not exists with id $realEstateId")
    }

    @Transactional(readOnly = true)
    open fun getAllRealEstates(realEstateSpecification: Specification<RealEstateEntity>?, pageable: Pageable): DataTransferResult<Page<RealEstateDTO>> {
        return Success(
            HttpStatus.OK,
            realEstateRepository.findAll(realEstateSpecification, pageable)
                .map {
                    it.toRealEstateDTO(getFilenamesByRealEstateId(it.id))
                }
        )
    }


    @Transactional(readOnly = true)
    open fun getRealEstatesByUserId(pageable: Pageable): DataTransferResult<Page<RealEstateDTO>> {
        val user = userService
            .userWithAuthorities
            .orNull() ?: return Error(HttpStatus.UNAUTHORIZED, "User not Authenticated")

        return Success(
            HttpStatus.OK,
            realEstateRepository.findByUserId(user.id, pageable)
                .map {
                    it.toRealEstateDTO(getFilenamesByRealEstateId(it.id))
                }
        )
    }


    fun createNewRealEstate(newRealEstateDTO: NewRealEstateDTO): DataTransferResult<Void> {
        val user = userService
            .userWithAuthorities
            .orNull() ?: return Error(HttpStatus.UNAUTHORIZED, "User not Authenticated")

        realEstateRepository.save(newRealEstateDTO.toRealEstateEntity(user))
        return Success(HttpStatus.CREATED)
    }

    fun updateRealEstate(realEstateId: Long, newRealEstateDTO: NewRealEstateDTO): DataTransferResult<Void> {
        val realEstate = realEstateRepository
            .findById(realEstateId)
            .orNull() ?: return Error(HttpStatus.NOT_FOUND, "Does not exists any recourse with id : $realEstateId")

        val user = userService
            .userWithAuthorities
            .orNull() ?: return Error(HttpStatus.UNAUTHORIZED, "User not Authenticated")

        if (user.id != realEstate.user.id) {
            return Error(HttpStatus.UNAUTHORIZED, "User not Authorized")
        }

        realEstateRepository.save(realEstate.toUpdatedRealEstateEntity(newRealEstateDTO))
        return Success(HttpStatus.OK)

    }

    fun deleteRealEstate(realEstateId: Long): DataTransferResult<Void> {
        val realEstate = realEstateRepository
            .findById(realEstateId)
            .orNull() ?: return Error(HttpStatus.NOT_FOUND, "Does not exists any recourse with id : $realEstateId")

        val user = userService
            .userWithAuthorities
            .orNull() ?: return Error(HttpStatus.UNAUTHORIZED, "User not Authenticated")

        if (user.id != realEstate.user.id) {
            return Error(HttpStatus.UNAUTHORIZED, "User not Authorized")
        }

        realEstateRepository.delete(realEstate)
        return Success(HttpStatus.OK)
    }

    private fun getFilenamesByRealEstateId(realEstateId: Long): List<String> =
        storageService.loadFiles(realEstateId)


    //TODO test it. optimize
    @Scheduled(fixedDelay = WEEK_IN_MILLISEC)
    fun sendSpectatorsCountToUsers() {
        userService.getAllManagedUsers(PageRequest.of(0, 100)).content
            .forEach { user ->
                realEstateRepository.findByUserId(user.id, PageRequest.of(0, 100)).content
                    .forEach {
                        mailService.sendEmail(
                            user.email,
                            "SpectatorsCount",
                            "Real-estates: ${it.name} SpectatorsCount: ${it.spectatorsCount}",
                            false,
                            false
                        )
                    }
            }
    }
}


