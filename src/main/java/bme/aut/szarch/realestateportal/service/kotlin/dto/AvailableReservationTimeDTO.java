package bme.aut.szarch.realestateportal.service.kotlin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * AvailableReservationTime
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-14T16:48:21.594559600+02:00[Europe/Belgrade]")

public class AvailableReservationTimeDTO {
  @JsonProperty("from ")
  OffsetDateTime from;

  @JsonProperty("to")
  OffsetDateTime to;

    public AvailableReservationTimeDTO from(OffsetDateTime from) {
    this.from = from;
    return this;
  }

  /**
   * The start of the available interval.
   * @return from
  */
  @ApiModelProperty(required = true, value = "The start of the available interval.")
  @NotNull

  @Valid

  public OffsetDateTime getFrom() {
    return from;
  }

  public void setFrom(OffsetDateTime from) {
    this.from = from;
  }

    public AvailableReservationTimeDTO to(OffsetDateTime to) {
    this.to = to;
    return this;
  }

  /**
   * The start of the available interval.
   * @return to
  */
  @ApiModelProperty(required = true, value = "The start of the available interval.")
  @NotNull

  @Valid

  public OffsetDateTime getTo() {
    return to;
  }

  public void setTo(OffsetDateTime to) {
    this.to = to;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
      AvailableReservationTimeDTO availableReservationTimeDTO = (AvailableReservationTimeDTO) o;
      return Objects.equals(this.from, availableReservationTimeDTO.from) &&
          Objects.equals(this.to, availableReservationTimeDTO.to);
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, to);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AvailableReservationTime {\n");

    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
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

