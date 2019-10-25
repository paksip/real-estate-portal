package bme.aut.szarch.realestateportal.service.kotlin.dto;

import bme.aut.szarch.realestateportal.service.kotlin.dto.NewRealEstateDTO.CategoryEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * RealEstateDetails
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-25T13:28:08.197256200+02:00[Europe/Belgrade]")

public class RealEstateDetailsDTO {
  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("location")
  private LocationDTO locationDTO;

  @JsonProperty("category")
  private CategoryEnum category;

  @JsonProperty("spectatorsCount")
  private Long spectatorsCount;

  @JsonProperty("squareMeter")
  private Integer squareMeter;

  @JsonProperty("price")
  private Integer price;

  @JsonProperty("numberOfRooms")
  private Integer numberOfRooms;

  @JsonProperty("hasBalncony")
  private Boolean hasBalncony;

  @JsonProperty("hasAirCondition")
  private Boolean hasAirCondition;

  @JsonProperty("ownerPhoneNumber")
  private String ownerPhoneNumber;

  @JsonProperty("filePaths")
  @Valid
  private List<String> filePaths = new ArrayList<>();

  @JsonProperty("reservations")
  @Valid
  private List<ReservationDTO> reservationDTOS = new ArrayList<>();

  @JsonProperty("userId")
  private Long userId;

  public RealEstateDetailsDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RealEstateDetailsDTO description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public RealEstateDetailsDTO location(LocationDTO locationDTO) {
    this.locationDTO = locationDTO;
    return this;
  }

  /**
   * Get location
   * @return location
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public LocationDTO getLocationDTO() {
    return locationDTO;
  }

  public void setLocationDTO(LocationDTO locationDTO) {
    this.locationDTO = locationDTO;
  }

  public RealEstateDetailsDTO category(CategoryEnum category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public CategoryEnum getCategory() {
    return category;
  }

  public void setCategory(CategoryEnum category) {
    this.category = category;
  }

  public RealEstateDetailsDTO spectatorsCount(Long spectatorsCount) {
    this.spectatorsCount = spectatorsCount;
    return this;
  }

  /**
   * Get spectatorsCount
   * @return spectatorsCount
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getSpectatorsCount() {
    return spectatorsCount;
  }

  public void setSpectatorsCount(Long spectatorsCount) {
    this.spectatorsCount = spectatorsCount;
  }

  public RealEstateDetailsDTO squareMeter(Integer squareMeter) {
    this.squareMeter = squareMeter;
    return this;
  }

  /**
   * Get squareMeter
   * @return squareMeter
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getSquareMeter() {
    return squareMeter;
  }

  public void setSquareMeter(Integer squareMeter) {
    this.squareMeter = squareMeter;
  }

  public RealEstateDetailsDTO price(Integer price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public RealEstateDetailsDTO numberOfRooms(Integer numberOfRooms) {
    this.numberOfRooms = numberOfRooms;
    return this;
  }

  /**
   * Get numberOfRooms
   * @return numberOfRooms
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getNumberOfRooms() {
    return numberOfRooms;
  }

  public void setNumberOfRooms(Integer numberOfRooms) {
    this.numberOfRooms = numberOfRooms;
  }

  public RealEstateDetailsDTO hasBalncony(Boolean hasBalncony) {
    this.hasBalncony = hasBalncony;
    return this;
  }

  /**
   * Get hasBalncony
   * @return hasBalncony
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Boolean getHasBalncony() {
    return hasBalncony;
  }

  public void setHasBalncony(Boolean hasBalncony) {
    this.hasBalncony = hasBalncony;
  }

  public RealEstateDetailsDTO hasAirCondition(Boolean hasAirCondition) {
    this.hasAirCondition = hasAirCondition;
    return this;
  }

  /**
   * Get hasAirCondition
   * @return hasAirCondition
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Boolean getHasAirCondition() {
    return hasAirCondition;
  }

  public void setHasAirCondition(Boolean hasAirCondition) {
    this.hasAirCondition = hasAirCondition;
  }

  public RealEstateDetailsDTO ownerPhoneNumber(String ownerPhoneNumber) {
    this.ownerPhoneNumber = ownerPhoneNumber;
    return this;
  }

  /**
   * Get ownerPhoneNumber
   * @return ownerPhoneNumber
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getOwnerPhoneNumber() {
    return ownerPhoneNumber;
  }

  public void setOwnerPhoneNumber(String ownerPhoneNumber) {
    this.ownerPhoneNumber = ownerPhoneNumber;
  }

  public RealEstateDetailsDTO filePaths(List<String> filePaths) {
    this.filePaths = filePaths;
    return this;
  }

  public RealEstateDetailsDTO addFilePathsItem(String filePathsItem) {
    this.filePaths.add(filePathsItem);
    return this;
  }

  /**
   * Get filePaths
   * @return filePaths
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public List<String> getFilePaths() {
    return filePaths;
  }

  public void setFilePaths(List<String> filePaths) {
    this.filePaths = filePaths;
  }

  public RealEstateDetailsDTO reservations(List<ReservationDTO> reservationDTOS) {
    this.reservationDTOS = reservationDTOS;
    return this;
  }

  public RealEstateDetailsDTO addReservationsItem(ReservationDTO reservationsItem) {
    this.reservationDTOS.add(reservationsItem);
    return this;
  }

  /**
   * Get reservations
   * @return reservations
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<ReservationDTO> getReservationDTOS() {
    return reservationDTOS;
  }

  public void setReservationDTOS(List<ReservationDTO> reservationDTOS) {
    this.reservationDTOS = reservationDTOS;
  }

  public RealEstateDetailsDTO userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RealEstateDetailsDTO realEstateDetailsDTO = (RealEstateDetailsDTO) o;
    return Objects.equals(this.name, realEstateDetailsDTO.name) &&
        Objects.equals(this.description, realEstateDetailsDTO.description) &&
        Objects.equals(this.locationDTO, realEstateDetailsDTO.locationDTO) &&
        Objects.equals(this.category, realEstateDetailsDTO.category) &&
        Objects.equals(this.spectatorsCount, realEstateDetailsDTO.spectatorsCount) &&
        Objects.equals(this.squareMeter, realEstateDetailsDTO.squareMeter) &&
        Objects.equals(this.price, realEstateDetailsDTO.price) &&
        Objects.equals(this.numberOfRooms, realEstateDetailsDTO.numberOfRooms) &&
        Objects.equals(this.hasBalncony, realEstateDetailsDTO.hasBalncony) &&
        Objects.equals(this.hasAirCondition, realEstateDetailsDTO.hasAirCondition) &&
        Objects.equals(this.ownerPhoneNumber, realEstateDetailsDTO.ownerPhoneNumber) &&
        Objects.equals(this.filePaths, realEstateDetailsDTO.filePaths) &&
        Objects.equals(this.reservationDTOS, realEstateDetailsDTO.reservationDTOS) &&
        Objects.equals(this.userId, realEstateDetailsDTO.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, locationDTO, category, spectatorsCount, squareMeter, price, numberOfRooms, hasBalncony, hasAirCondition, ownerPhoneNumber, filePaths, reservationDTOS, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RealEstateDetails {\n");

    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    location: ").append(toIndentedString(locationDTO)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    spectatorsCount: ").append(toIndentedString(spectatorsCount)).append("\n");
    sb.append("    squareMeter: ").append(toIndentedString(squareMeter)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    numberOfRooms: ").append(toIndentedString(numberOfRooms)).append("\n");
    sb.append("    hasBalncony: ").append(toIndentedString(hasBalncony)).append("\n");
    sb.append("    hasAirCondition: ").append(toIndentedString(hasAirCondition)).append("\n");
    sb.append("    ownerPhoneNumber: ").append(toIndentedString(ownerPhoneNumber)).append("\n");
    sb.append("    filePaths: ").append(toIndentedString(filePaths)).append("\n");
    sb.append("    reservations: ").append(toIndentedString(reservationDTOS)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

