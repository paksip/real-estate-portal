package bme.aut.szarch.realestateportal.service.kotlin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.String;
import java.util.Objects;

/**
 * Reservation
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-25T13:28:08.197256200+02:00[Europe/Belgrade]")

public class ReservationDTO {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("from")
  private String from;

  @JsonProperty("to")
  private String to;

  @JsonProperty("isFree")
  private Boolean isFree;

  public ReservationDTO id(Long id) {
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

  public ReservationDTO from(String from) {
    this.from = from;
    return this;
  }

  /**
   * The start of the reservation interval.
   * @return from
  */
  @ApiModelProperty(required = true, value = "The start of the reservation interval.")
  @NotNull

  @Valid

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public ReservationDTO to(String to) {
    this.to = to;
    return this;
  }

  /**
   * The end of the reservation interval.
   * @return to
  */
  @ApiModelProperty(required = true, value = "The end of the reservation interval.")
  @NotNull

  @Valid

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public ReservationDTO isFree(Boolean isFree) {
    this.isFree = isFree;
    return this;
  }

  /**
   * Get isFree
   * @return isFree
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Boolean getIsFree() {
    return isFree;
  }

  public void setIsFree(Boolean isFree) {
    this.isFree = isFree;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReservationDTO reservationDTO = (ReservationDTO) o;
    return Objects.equals(this.id, reservationDTO.id) &&
        Objects.equals(this.from, reservationDTO.from) &&
        Objects.equals(this.to, reservationDTO.to) &&
        Objects.equals(this.isFree, reservationDTO.isFree);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, from, to, isFree);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Reservation {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
    sb.append("    isFree: ").append(toIndentedString(isFree)).append("\n");
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

