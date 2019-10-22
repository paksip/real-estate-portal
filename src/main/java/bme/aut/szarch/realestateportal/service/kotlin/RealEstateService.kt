package bme.aut.szarch.realestateportal.service.kotlin

import bme.aut.szarch.realestateportal.domain.kotlin.RealEstateEntity
import bme.aut.szarch.realestateportal.repository.kotlin.RealEstateRepository
import bme.aut.szarch.realestateportal.service.UserService
import bme.aut.szarch.realestateportal.service.kotlin.dto.NewRealEstateDTO
import bme.aut.szarch.realestateportal.service.kotlin.dto.RealEstateDTO
import bme.aut.szarch.realestateportal.service.kotlin.dto.RealEstateDetailsDTO
import bme.aut.szarch.realestateportal.service.kotlin.extensions.orNull
import bme.aut.szarch.realestateportal.service.kotlin.mapper.toLocationEntity
import bme.aut.szarch.realestateportal.service.kotlin.mapper.toRealEstateDTO
import bme.aut.szarch.realestateportal.service.kotlin.mapper.toRealEstateDetailsDTO
import bme.aut.szarch.realestateportal.service.kotlin.mapper.toRealEstateEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Error
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Success
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
open class RealEstateService(
    private val realEstateRepository: RealEstateRepository,
    private val userService: UserService,
    private val storageService: StorageServiceImp
) {

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

        val updatedRealEstate = realEstate.copy(
            name = newRealEstateDTO.name,
            description = newRealEstateDTO.description,
            category = newRealEstateDTO.category,
            location = newRealEstateDTO.location.toLocationEntity(),
            squareMeter = newRealEstateDTO.squareMeter,
            price = newRealEstateDTO.price,
            numberOfRooms = newRealEstateDTO.numberOfRooms,
            hasBalcony = newRealEstateDTO.hasBalncony,
            hasAircondition = newRealEstateDTO.hasBalncony,
            ownerPhoneNumber = newRealEstateDTO.ownerPhoneNumber
        )
        realEstateRepository.save(updatedRealEstate)
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


}





