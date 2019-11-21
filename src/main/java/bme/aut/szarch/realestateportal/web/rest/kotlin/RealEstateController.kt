package bme.aut.szarch.realestateportal.web.rest.kotlin

import bme.aut.szarch.realestateportal.domain.kotlin.RealEstateEntity
import bme.aut.szarch.realestateportal.security.AuthoritiesConstants
import bme.aut.szarch.realestateportal.service.kotlin.dto.*
import com.sipios.springsearch.anotation.SearchSpec
import io.swagger.annotations.*
import org.springframework.core.io.Resource
import org.springframework.data.domain.Page
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid
import javax.validation.constraints.NotNull

@RestController
@Validated
@Api(value = "realestates", description = "the realestates API")
@RequestMapping("/api/realestates")
interface RealEstateController {

    @ApiOperation(value = "Create new real-estate", nickname = "createNewRealEstate", notes = "Create a new real-estate", tags = ["REAL-ESTATE"])
    @ApiResponses(value = [ApiResponse(code = 201, message = "Created"), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(value = ["/"], consumes = ["application/json"], method = [RequestMethod.POST])
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
    fun createNewRealEstate(@ApiParam(value = "") @Valid @RequestBody newRealEstate: NewRealEstateDTO): ResponseEntity<Void>


    @ApiOperation(value = "Create a new reservation", nickname = "createNewReservation", notes = "Create a new reservation", tags = ["RESERVATION"])
    @ApiResponses(value = [ApiResponse(code = 201, message = "Created"), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(value = ["/{realEstateId}/reservations"], consumes = ["application/json"], method = [RequestMethod.POST])
    fun creatNewAvailableReservationTime(@ApiParam(value = "", required = true) @PathVariable("realEstateId") realEstateId: Long, @ApiParam(value = "") @Valid @RequestBody availableReservationTime: AvailableReservationTimeDTO): ResponseEntity<Void>


    @ApiOperation(value = "Delete a real-estate", nickname = "deleteRealEstate", notes = "Delete a real-estate", tags = ["REAL-ESTATE"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "OK"), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(value = ["/{realEstateId}"], method = [RequestMethod.DELETE])
    fun deleteRealEstate(@ApiParam(value = "", required = true) @PathVariable("realEstateId") realEstateId: Long): ResponseEntity<Void>


    @ApiOperation(value = "Delete a reservation", nickname = "deleteReservation", notes = "Deleten en existing reservation", tags = ["RESERVATION"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "OK"), ApiResponse(code = 403, message = "Forbidden"), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(value = ["/{realEstateId}/reservations/{reservationId}"], method = [RequestMethod.DELETE])
    fun deleteReservation(@ApiParam(value = "", required = true) @PathVariable("realEstateId") realEstateId: Long, @ApiParam(value = "", required = true) @PathVariable("reservationId") reservationId: Long): ResponseEntity<Void>


    @ApiOperation(value = "Get real-estates", nickname = "getAllRealEstates", notes = "Get all real-estates. There is possibilities for sort the result by the query params.", response = RealEstateDTO::class, responseContainer = "List", tags = ["REAL-ESTATE"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "OK", response = RealEstateDTO::class, responseContainer = "List"), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(value = [""], produces = ["application/json"], method = [RequestMethod.GET])
    fun getAllRealEstates(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "page", required = true) page: Int?, @NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "offset", required = true) offset: Int?, @SearchSpec specs: Specification<RealEstateEntity>): ResponseEntity<Page<RealEstateDTO>>


    @ApiOperation(value = "Get all reservation", nickname = "getAllReservation", notes = "Get all reservation", response = ReservationDTO::class, responseContainer = "List", tags = ["RESERVATION"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "OK", response = ReservationDTO::class, responseContainer = "List"), ApiResponse(code = 404, message = "Not Found"), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(value = ["/{realEstateId}/reservations"], produces = ["application/json"], method = [RequestMethod.GET])
    fun getAllReservation(@ApiParam(value = "", required = true) @PathVariable("realEstateId") realEstateId: Long): ResponseEntity<List<ReservationDTO>>


    @ApiOperation(value = "Get a file", nickname = "getFile", notes = "Get a File", response = Resource::class, tags = ["REAL-ESTATE"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "OK", response = Resource::class), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(value = ["/files/{fileName}"], produces = ["application/json"], method = [RequestMethod.GET])
    fun downloadFile(@ApiParam(value = "", required = true) @PathVariable("fileName") fileName: String): ResponseEntity<Resource>


    @ApiOperation(value = "Get real-estates", nickname = "getRealEstateById", notes = "Get the details of a real-estate", response = RealEstateDetailsDTO::class, tags = ["REAL-ESTATE"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "OK", response = RealEstateDetailsDTO::class), ApiResponse(code = 404, message = "Not Found"), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(value = ["/{realEstateId}"], produces = ["application/json"], method = [RequestMethod.GET])
    fun getRealEstateById(@ApiParam(value = "", required = true) @PathVariable("realEstateId") realEstateId: Long): ResponseEntity<RealEstateDetailsDTO>


    @ApiOperation(value = "Get the user's real-estates", nickname = "getRealEstatesByUserId", notes = "Get user's real-estates", response = RealEstateDTO::class, responseContainer = "List", tags = ["REAL-ESTATE"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "OK", response = RealEstateDTO::class, responseContainer = "List"), ApiResponse(code = 404, message = "Not Found"), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(value = ["/ownrealestates"], produces = ["application/json"], method = [RequestMethod.GET])
    fun getRealEstatesByUserId(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "page", required = true) page: Int?, @NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "offset", required = true) offset: Int?): ResponseEntity<Page<RealEstateDTO>>


    @ApiOperation(value = "Get reservation details", nickname = "getReservationDetails", notes = "Get reservation details", response = ReservationDetailsDTO::class, tags = ["RESERVATION"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "OK", response = ReservationDetailsDTO::class), ApiResponse(code = 404, message = "Not Found"), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(value = ["/{realEstateId}/reservations/{reservationId}"], produces = ["application/json"], method = [RequestMethod.GET])
    fun getReservationDetails(@ApiParam(value = "", required = true) @PathVariable("realEstateId") realEstateId: Long, @ApiParam(value = "", required = true) @PathVariable("reservationId") reservationId: Long): ResponseEntity<ReservationDetailsDTO>


    @ApiOperation(value = "Make a new reservation", nickname = "newReservation", notes = "A customer create a new reservation.", tags = ["REAL-ESTATE"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "OK")])
    @RequestMapping(value = ["/{realEstateId}/reservations/{reservationId}"], consumes = ["application/json"], method = [RequestMethod.PUT])
    fun makeNewReservation(@ApiParam(value = "", required = true) @PathVariable("realEstateId") realEstateId: Long, @ApiParam(value = "", required = true) @PathVariable("reservationId") reservationId: Long, @ApiParam(value = "") @Valid @RequestBody newReservation: NewReservationDTO): ResponseEntity<Void>


    @ApiOperation(value = "Update realEstate", nickname = "updateRealEstate", notes = "Update an existing real-estate", tags = ["REAL-ESTATE"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "OK")])
    @RequestMapping(value = ["/{realEstateId}"], consumes = ["application/json"], method = [RequestMethod.PUT])
    fun updateRealEstate(@ApiParam(value = "", required = true) @PathVariable("realEstateId") realEstateId: Long, @ApiParam(value = "") @Valid @RequestBody newRealEstate: NewRealEstateDTO): ResponseEntity<Void>

    @ApiOperation(value = "Upload files", nickname = "uploadFiles", notes = "Upload files", tags = ["REAL-ESTATE"])
    @ApiResponses(value = [ApiResponse(code = 201, message = "Created"), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(value = ["/{realEstateId}/files"], method = [RequestMethod.POST])
    fun uploadFiles(@ApiParam(value = "", required = true) @PathVariable("realEstateId") realEstateId: Long, file: MultipartFile): ResponseEntity<Void>
}
