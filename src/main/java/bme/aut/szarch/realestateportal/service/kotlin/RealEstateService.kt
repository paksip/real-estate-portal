package bme.aut.szarch.realestateportal.service.kotlin

import bme.aut.szarch.realestateportal.repository.kotlin.RealEstateRepository
import bme.aut.szarch.realestateportal.service.UserService
import bme.aut.szarch.realestateportal.service.kotlin.dto.NewRealEstateDTO
import bme.aut.szarch.realestateportal.service.kotlin.dto.RealEstateDTO
import bme.aut.szarch.realestateportal.service.kotlin.dto.RealEstateDetailsDTO
import bme.aut.szarch.realestateportal.service.kotlin.extensions.orNull
import bme.aut.szarch.realestateportal.service.kotlin.mapper.toRealEstateDetailsDTO
import bme.aut.szarch.realestateportal.service.kotlin.mapper.toRealEstateEntity
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Failure
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Success
import bme.aut.szarch.realestateportal.service.kotlin.util.result.StorageMethodResult
import bme.aut.szarch.realestateportal.service.kotlin.util.toUrl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Stream
import kotlin.streams.toList


@Service
@Transactional
open class RealEstateService(
    private val realEstateRepository: RealEstateRepository,
    private val userService: UserService,
    private val storageService: StorageServiceImp
) {

    @Transactional(readOnly = true)
    open fun getRealEstateById(realEstateId: Long): DataTransferResult<RealEstateDetailsDTO> {
        val files = when (val storageResult = storageService.loadFiles(realEstateId)) {
            is StorageMethodResult.SuccessWithResult -> storageResult.result
            is StorageMethodResult.Failed -> return Failure(HttpStatus.INTERNAL_SERVER_ERROR, "IOException occurred during file uploading")
            else -> Stream.empty()
        }

        realEstateRepository.findById(realEstateId).orNull()?.let { realEstate ->
            return Success(
                HttpStatus.OK,
                realEstate.toRealEstateDetailsDTO(
                    files.toList().map { it.toUrl<RealEstateService>() }
                )
            )
        } ?: return Failure(HttpStatus.NOT_FOUND, "Resource does not exists with id $realEstateId")
    }

    @Transactional(readOnly = true)
    open fun getAllRealEstates(search: String, page: Int?, offset: Int?): ResponseEntity<List<RealEstateDTO>> {
        //TODO ez bonyolult ezt majd a végén!
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    }

    @Transactional(readOnly = true)
    open fun getRealEstatesByUserId(page: Int, offset: Int): DataTransferResult<RealEstateDetailsDTO> {
        val user = userService
            .userWithAuthorities
            .orNull() ?: return Failure(HttpStatus.UNAUTHORIZED, "User not Authenticated")
        //TODO ez bonyolult ezt majd a végén!
        return Success(HttpStatus.OK)
    }


    fun createNewRealEstate(newRealEstateDTO: NewRealEstateDTO): DataTransferResult<Void> {
        val user = userService
            .userWithAuthorities
            .orNull() ?: return Failure(HttpStatus.UNAUTHORIZED, "User not Authenticated")

        realEstateRepository.save(newRealEstateDTO.toRealEstateEntity(user))
        return Success(HttpStatus.OK)
    }

    fun updateRealEstate(realEstateId: Long, newRealEstateDTO: NewRealEstateDTO): DataTransferResult<Void> {
        val realEstate = realEstateRepository
            .findById(realEstateId)
            .orNull() ?: return Failure(HttpStatus.NOT_FOUND, "Does not exists any recourse with id : $realEstateId")

        val user = userService
            .userWithAuthorities
            .orNull() ?: return Failure(HttpStatus.UNAUTHORIZED, "User not Authenticated")

        if (user.id != realEstate.user.id) {
            Failure(HttpStatus.UNAUTHORIZED, "User not Authorized")
        }

        realEstateRepository.delete(realEstate)
        realEstateRepository.save(newRealEstateDTO.toRealEstateEntity(user))
        return Success(HttpStatus.OK)

    }

    fun deleteRealEstate(realEstateId: Long): DataTransferResult<Void> {
        val realEstate = realEstateRepository
            .findById(realEstateId)
            .orNull() ?: return Failure(HttpStatus.NOT_FOUND, "Does not exists any recourse with id : $realEstateId")

        val user = userService
            .userWithAuthorities
            .orNull() ?: return Failure(HttpStatus.UNAUTHORIZED, "User not Authenticated")

        if (user.id != realEstate.user.id) {
            Failure(HttpStatus.UNAUTHORIZED, "User not Authorized")
        }

        realEstateRepository.delete(realEstate)
        return Success(HttpStatus.OK)
    }
}





