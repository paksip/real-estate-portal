package bme.aut.szarch.realestateportal.service.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AvailableReservationTime
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-14T16:48:21.594559600+02:00[Europe/Belgrade]")

public class AvailableReservationTime   {
  @JsonProperty("from ")
  private OffsetDateTime from;

  @JsonProperty("to")
  private OffsetDateTime to;

  public AvailableReservationTime from(OffsetDateTime from) {
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

  public AvailableReservationTime to(OffsetDateTime to) {
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
    AvailableReservationTime availableReservationTime = (AvailableReservationTime) o;
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

