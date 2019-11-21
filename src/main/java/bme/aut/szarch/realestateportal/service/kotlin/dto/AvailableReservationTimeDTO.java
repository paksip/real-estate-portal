package bme.aut.szarch.realestateportal.service.kotlin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.String;
import java.util.Objects;

/**
 * AvailableReservationTime
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-25T13:28:08.197256200+02:00[Europe/Belgrade]")
public class AvailableReservationTimeDTO {
  @JsonProperty("from")
  private String from;

  @JsonProperty("to")
  private String to;

  public AvailableReservationTimeDTO from(String from) {
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

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public AvailableReservationTimeDTO to(String to) {
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

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
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
    AvailableReservationTimeDTO availableReservationTime = (AvailableReservationTimeDTO) o;
    return Objects.equals(this.from, availableReservationTime.from) &&
        Objects.equals(this.to, availableReservationTime.to);
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

