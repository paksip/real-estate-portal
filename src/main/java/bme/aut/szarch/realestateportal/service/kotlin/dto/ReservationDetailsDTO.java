package bme.aut.szarch.realestateportal.service.kotlin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * ReservationDetails
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-14T16:48:21.594559600+02:00[Europe/Belgrade]")

public class ReservationDetailsDTO {
  @JsonProperty("from")
  OffsetDateTime from;

  @JsonProperty("to")
  OffsetDateTime to;

  @JsonProperty("emailAddress")
  String emailAddress;

  @JsonProperty("phoneNumber")
  String phoneNumber;

  @JsonProperty("message")
  String message;

  @JsonProperty("userName")
  String userName;

    public ReservationDetailsDTO from(OffsetDateTime from) {
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

  public OffsetDateTime getFrom() {
    return from;
  }

  public void setFrom(OffsetDateTime from) {
    this.from = from;
  }

    public ReservationDetailsDTO to(OffsetDateTime to) {
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

  public OffsetDateTime getTo() {
    return to;
  }

  public void setTo(OffsetDateTime to) {
    this.to = to;
  }

    public ReservationDetailsDTO emailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

  /**
   * Get emailAddress
   * @return emailAddress
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

    public ReservationDetailsDTO phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   * @return phoneNumber
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

    public ReservationDetailsDTO message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  */
  @ApiModelProperty(value = "")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

    public ReservationDetailsDTO userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * Get userName
   * @return userName
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
      ReservationDetailsDTO reservationDetailsDTO = (ReservationDetailsDTO) o;
      return Objects.equals(this.from, reservationDetailsDTO.from) &&
          Objects.equals(this.to, reservationDetailsDTO.to) &&
          Objects.equals(this.emailAddress, reservationDetailsDTO.emailAddress) &&
          Objects.equals(this.phoneNumber, reservationDetailsDTO.phoneNumber) &&
          Objects.equals(this.message, reservationDetailsDTO.message) &&
          Objects.equals(this.userName, reservationDetailsDTO.userName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, to, emailAddress, phoneNumber, message, userName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReservationDetails {\n");

    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
    sb.append("    emailAddress: ").append(toIndentedString(emailAddress)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
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

