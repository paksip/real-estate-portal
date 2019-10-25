package bme.aut.szarch.realestateportal.service.kotlin.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * NewRealEstate
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-25T13:28:08.197256200+02:00[Europe/Belgrade]")

public class NewRealEstateDTO {
  @JsonProperty("name")
  private String name;

  /**
   * Gets or Sets category
   */
  public enum CategoryEnum {
    FLAT("FLAT"),

    PANEL("PANEL"),

    COUNTRY_HOUSE("COUNTRY_HOUSE"),

    LUXORY_HOUSE("LUXORY_HOUSE"),

    DETACHED_HOUSE("DETACHED_HOUSE");

    private String value;

    CategoryEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CategoryEnum fromValue(String value) {
      for (CategoryEnum b : CategoryEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("category")
  private CategoryEnum category;

  @JsonProperty("location")
  private LocationDTO location;

  @JsonProperty("description")
  private String description;

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

  public NewRealEstateDTO name(String name) {
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

  public NewRealEstateDTO category(CategoryEnum category) {
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

  public NewRealEstateDTO location(LocationDTO locationDTO) {
    this.location = locationDTO;
    return this;
  }

  /**
   * Get location
   * @return location
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public LocationDTO getLocation() {
    return location;
  }

  public void setLocation(LocationDTO locationDTO) {
    this.location = locationDTO;
  }

  public NewRealEstateDTO description(String description) {
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

  public NewRealEstateDTO squareMeter(Integer squareMeter) {
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

  public NewRealEstateDTO price(Integer price) {
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

  public NewRealEstateDTO numberOfRooms(Integer numberOfRooms) {
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

  public NewRealEstateDTO hasBalncony(Boolean hasBalncony) {
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

  public NewRealEstateDTO hasAirCondition(Boolean hasAirCondition) {
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

  public NewRealEstateDTO ownerPhoneNumber(String ownerPhoneNumber) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewRealEstateDTO newRealEstateDTO = (NewRealEstateDTO) o;
    return Objects.equals(this.name, newRealEstateDTO.name) &&
        Objects.equals(this.category, newRealEstateDTO.category) &&
        Objects.equals(this.location, newRealEstateDTO.location) &&
        Objects.equals(this.description, newRealEstateDTO.description) &&
        Objects.equals(this.squareMeter, newRealEstateDTO.squareMeter) &&
        Objects.equals(this.price, newRealEstateDTO.price) &&
        Objects.equals(this.numberOfRooms, newRealEstateDTO.numberOfRooms) &&
        Objects.equals(this.hasBalncony, newRealEstateDTO.hasBalncony) &&
        Objects.equals(this.hasAirCondition, newRealEstateDTO.hasAirCondition) &&
        Objects.equals(this.ownerPhoneNumber, newRealEstateDTO.ownerPhoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, category, location, description, squareMeter, price, numberOfRooms, hasBalncony, hasAirCondition, ownerPhoneNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewRealEstate {\n");

    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    squareMeter: ").append(toIndentedString(squareMeter)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    numberOfRooms: ").append(toIndentedString(numberOfRooms)).append("\n");
    sb.append("    hasBalncony: ").append(toIndentedString(hasBalncony)).append("\n");
    sb.append("    hasAirCondition: ").append(toIndentedString(hasAirCondition)).append("\n");
    sb.append("    ownerPhoneNumber: ").append(toIndentedString(ownerPhoneNumber)).append("\n");
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

