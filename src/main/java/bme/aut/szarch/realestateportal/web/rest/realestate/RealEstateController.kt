package bme.aut.szarch.realestateportal.web.rest.realestate

import bme.aut.szarch.realestateportal.web.api.model.*
import io.swagger.annotations.*
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Pattern

@Validated
@Api(value = "realestates", description = "the realestates API")
@RequestMapping("/default")
interface RealEstateController {

    @ApiOperation(value = "Create new real-estate", nickname = "createNewRealEstate", notes = "Create a new real-estate", tags = ["REAL-ESTATE"])
    @ApiResponses(value = [ApiResponse(code = 201, message = "Created"), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(consumes = ["application/json"], method = [RequestMethod.POST])
    fun createNewRealEstate(@ApiParam(value = "") @Valid @RequestBody newRealEstate: NewRealEstate): ResponseEntity<Void>


    @ApiOperation(value = "Create a new reservation", nickname = "createNewReservation", notes = "Create a new reservation", tags = ["RESERVATION"])
    @ApiResponses(value = [ApiResponse(code = 201, message = "Created"), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(value = ["/{realEstateId}/reservations"], consumes = ["application/json"], method = [RequestMethod.POST])
    fun createNewReservation(@ApiParam(value = "", required = true) @PathVariable("realEstateId") realEstateId: String, @ApiParam(value = "") @Valid @RequestBody reservationDetails: ReservationDetails): ResponseEntity<Void>


    @ApiOperation(value = "Delete a real-estate", nickname = "deleteRealEstate", notes = "Delete a real-estate", tags = ["REAL-ESTATE"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "OK"), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(value = ["/{realEstateId}"], method = [RequestMethod.DELETE])
    fun deleteRealEstate(@ApiParam(value = "", required = true) @PathVariable("realEstateId") realEstateId: String): ResponseEntity<Void>


    @ApiOperation(value = "Delete a reservation", nickname = "deleteReservation", notes = "Deleten en existing reservation", tags = ["RESERVATION"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "OK"), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(value = ["/{realEstateId}/reservations/{reservationId}"], method = [RequestMethod.DELETE])
    fun deleteReservation(@ApiParam(value = "", required = true) @PathVariable("realEstateId") realEstateId: String, @ApiParam(value = "", required = true) @PathVariable("reservationId") reservationId: String): ResponseEntity<Void>


    @ApiOperation(value = "Get real-estates", nickname = "getAllRealEstates", notes = "Get all real-estates. There is possibilities for sort the result by the query params.", response = RealEstate::class, responseContainer = "List", tags = ["REAL-ESTATE"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "OK", response = RealEstate::class, responseContainer = "List"), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(produces = ["application/json"], method = [RequestMethod.GET])
    fun getAllRealEstates(@Pattern(regexp = "(\\w+?)(:|<|>)(\\w+?),") @ApiParam(value = "") @Valid @RequestParam(value = "search", required = false) search: String): ResponseEntity<List<RealEstate>>


    @ApiOperation(value = "Get all reservation", nickname = "getAllReservation", notes = "Get all reservation", response = Reservation::class, responseContainer = "List", tags = ["RESERVATION"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "OK", response = Reservation::class, responseContainer = "List"), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(value = ["/{realEstateId}/reservations"], produces = ["application/json"], method = [RequestMethod.GET])
    fun getAllReservation(@ApiParam(value = "", required = true) @PathVariable("realEstateId") realEstateId: String): ResponseEntity<List<Reservation>>


    @ApiOperation(value = "Get real-estates", nickname = "getRealEstateById", notes = "Get the details of a real-estate", response = RealEstateDetails::class, tags = ["REAL-ESTATE"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "OK", response = RealEstateDetails::class), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(value = ["/{realEstateId}"], produces = ["application/json"], method = [RequestMethod.GET])
    fun getRealEstateById(@ApiParam(value = "", required = true) @PathVariable("realEstateId") realEstateId: String): ResponseEntity<RealEstateDetails>


    @ApiOperation(value = "Get reservation details", nickname = "getReservationDetails", notes = "Get reservation details", response = ReservationDetails::class, tags = ["RESERVATION"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "OK", response = ReservationDetails::class), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(value = ["/{realEstateId}/reservations/{reservationId}"], produces = ["application/json"], method = [RequestMethod.GET])
    fun getReservationDetails(@ApiParam(value = "", required = true) @PathVariable("realEstateId") realEstateId: String, @ApiParam(value = "", required = true) @PathVariable("reservationId") reservationId: String): ResponseEntity<ReservationDetails>


    @ApiOperation(value = "Update realEstate", nickname = "updateRealEstate", notes = "Update an existing real-estate", tags = ["REAL-ESTATE"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "OK")])
    @RequestMapping(value = ["/{realEstateId}"], consumes = ["application/json"], method = [RequestMethod.PUT])
    fun updateRealEstate(@ApiParam(value = "", required = true) @PathVariable("realEstateId") realEstateId: String, @ApiParam(value = "") @Valid @RequestBody newRealEstate: NewRealEstate): ResponseEntity<Void>


    @ApiOperation(value = "update a reservation", nickname = "updateReservation", notes = "Update an old reservation", tags = ["RESERVATION"])
    @ApiResponses(value = [ApiResponse(code = 200, message = "OK"), ApiResponse(code = 500, message = "Internal Server Error")])
    @RequestMapping(value = ["/{realEstateId}/reservations/{reservationId}"], consumes = ["application/json"], method = [RequestMethod.PUT])
    fun updateReservation(@ApiParam(value = "", required = true) @PathVariable("realEstateId") realEstateId: String, @ApiParam(value = "", required = true) @PathVariable("reservationId") reservationId: String, @ApiParam(value = "") @Valid @RequestBody reservationDetails: ReservationDetails): ResponseEntity<Void>
}
