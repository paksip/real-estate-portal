package bme.aut.szarch.realestateportal.service.kotlin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * RealEstate
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-14T16:48:21.594559600+02:00[Europe/Belgrade]")

public class RealEstateDTO {
  @JsonProperty("id")
  Long id;

  @JsonProperty("name")
  String name;

  @JsonProperty("spectatorsCount")
  Long spectatorsCount;

  @JsonProperty("price")
  Integer price;

  @JsonProperty("squareMeter")
  Integer squareMeter;

  @JsonProperty("numberOfRooms")
  Integer numberOfRooms;

  @JsonProperty("filePaths")
  @Valid
  List<String> filePaths = null;

    public RealEstateDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

    public RealEstateDTO name(String name) {
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

    public RealEstateDTO spectatorsCount(Long spectatorsCount) {
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

    public RealEstateDTO price(Integer price) {
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

    public RealEstateDTO squareMeter(Integer squareMeter) {
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

    public RealEstateDTO numberOfRooms(Integer numberOfRooms) {
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

    public RealEstateDTO filePaths(List<String> filePaths) {
    this.filePaths = filePaths;
    return this;
  }

    public RealEstateDTO addFilePathsItem(String filePathsItem) {
    if (this.filePaths == null) {
      this.filePaths = new ArrayList<>();
    }
    this.filePaths.add(filePathsItem);
    return this;
  }

  /**
   * Get filePaths
   * @return filePaths
  */
  @ApiModelProperty(value = "")


  public List<String> getFilePaths() {
    return filePaths;
  }

  public void setFilePaths(List<String> filePaths) {
    this.filePaths = filePaths;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
      RealEstateDTO realEstateDTO = (RealEstateDTO) o;
      return Objects.equals(this.id, realEstateDTO.id) &&
          Objects.equals(this.name, realEstateDTO.name) &&
          Objects.equals(this.spectatorsCount, realEstateDTO.spectatorsCount) &&
          Objects.equals(this.price, realEstateDTO.price) &&
          Objects.equals(this.squareMeter, realEstateDTO.squareMeter) &&
          Objects.equals(this.numberOfRooms, realEstateDTO.numberOfRooms) &&
          Objects.equals(this.filePaths, realEstateDTO.filePaths);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, spectatorsCount, price, squareMeter, numberOfRooms, filePaths);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RealEstate {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    spectatorsCount: ").append(toIndentedString(spectatorsCount)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    squareMeter: ").append(toIndentedString(squareMeter)).append("\n");
    sb.append("    numberOfRooms: ").append(toIndentedString(numberOfRooms)).append("\n");
    sb.append("    filePaths: ").append(toIndentedString(filePaths)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

