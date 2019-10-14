package bme.aut.szarch.realestateportal.service.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RealEstate
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-14T16:48:21.594559600+02:00[Europe/Belgrade]")

public class RealEstate   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("spectatorsCount")
  private Long spectatorsCount;

  @JsonProperty("price")
  private Integer price;

  @JsonProperty("squareMeter")
  private Integer squareMeter;

  @JsonProperty("numberOfRooms")
  private Integer numberOfRooms;

  @JsonProperty("filePaths")
  @Valid
  private List<String> filePaths = null;

  public RealEstate id(Long id) {
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

  public RealEstate name(String name) {
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

  public RealEstate spectatorsCount(Long spectatorsCount) {
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

  public RealEstate price(Integer price) {
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

  public RealEstate squareMeter(Integer squareMeter) {
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

  public RealEstate numberOfRooms(Integer numberOfRooms) {
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

  public RealEstate filePaths(List<String> filePaths) {
    this.filePaths = filePaths;
    return this;
  }

  public RealEstate addFilePathsItem(String filePathsItem) {
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
    RealEstate realEstate = (RealEstate) o;
    return Objects.equals(this.id, realEstate.id) &&
        Objects.equals(this.name, realEstate.name) &&
        Objects.equals(this.spectatorsCount, realEstate.spectatorsCount) &&
        Objects.equals(this.price, realEstate.price) &&
        Objects.equals(this.squareMeter, realEstate.squareMeter) &&
        Objects.equals(this.numberOfRooms, realEstate.numberOfRooms) &&
        Objects.equals(this.filePaths, realEstate.filePaths);
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
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

