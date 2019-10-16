package bme.aut.szarch.realestateportal.service

import bme.aut.szarch.realestateportal.repository.RealEstateRepository
import bme.aut.szarch.realestateportal.repository.ReservationRepository
import bme.aut.szarch.realestateportal.service.DataTransferResult.Failure
import bme.aut.szarch.realestateportal.service.DataTransferResult.Success
import bme.aut.szarch.realestateportal.service.dto.NewRealEstate
import bme.aut.szarch.realestateportal.service.dto.RealEstate
import bme.aut.szarch.realestateportal.service.dto.RealEstateDetails
import bme.aut.szarch.realestateportal.service.mapper.toRealEstateDetailsDTO
import bme.aut.szarch.realestateportal.service.mapper.toRealEstateEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import java.nio.file.Path
import java.util.*
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
    open fun getRealEstateById(realEstateId: Long): DataTransferResult<RealEstateDetails> {
        val files = when (val storageResult = storageService.loadFiles(realEstateId)) {
            is StorageMethodResult.SuccessWithResult -> storageResult.result
            is StorageMethodResult.Failed -> Stream.empty()
            else -> Stream.empty()
        }

        realEstateRepository.findById(realEstateId).orNull()?.let { realEstate ->
            return Success(
                HttpStatus.OK,
                realEstate.toRealEstateDetailsDTO(
                    files.toList().map { it.toUrl(this) }
                )
            )
        }
        return Failure(HttpStatus.NOT_FOUND, "Resource does not exists with id $realEstateId")
    }

    @Transactional(readOnly = true)
    open fun getAllRealEstates(search: String, page: Int?, offset: Int?): ResponseEntity<List<RealEstate>> {
        //TODO ez bonyolult ezt majd a végén!
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    }

    @Transactional(readOnly = true)
    open fun getRealEstatesByUserId(page: Int, offset: Int): ResponseEntity<List<RealEstate>> {
        //TODO ez bonyolult ezt majd a végén!
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    fun createNewRealEstate(newRealEstate: NewRealEstate): DataTransferResult<RealEstateDetails> {
        val user = userService
            .userWithAuthorities
            .orNull() ?: return Failure(HttpStatus.UNAUTHORIZED, "User not Authenticated")

        realEstateRepository.save(newRealEstate.toRealEstateEntity(user))
        return Success(HttpStatus.OK)

    }

    fun updateRealEstate(realEstateId: Long, newRealEstate: NewRealEstate): DataTransferResult<RealEstateDetails> {
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
        realEstateRepository.save(newRealEstate.toRealEstateEntity(user))
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


fun <T> Optional<T>.orNull(): T? = orElse(null)

inline fun <reified T : Any> Path.toUrl(containerClass: T): String {
    return MvcUriComponentsBuilder.fromMethodName(T::class.java,
        "downloadFile", this.fileName.toString()).build().toString()
}

