package bme.aut.szarch.realestateportal.web.rest.vm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * AvailableReservationTime
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-11T19:25:26.219837100+02:00[Europe/Berlin]")

public class AvailableReservationTime {
    @JsonProperty("from ")
    private String from;

    @JsonProperty("to")
    private String to;

    public AvailableReservationTime from(String from) {
        this.from = from;
        return this;
    }

    /**
     * The start of the available interval.
     *
     * @return from
     */
    @ApiModelProperty(required = true, value = "The start of the available interval.")
    @NotNull


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public AvailableReservationTime to(String to) {
        this.to = to;
        return this;
    }

    /**
     * The start of the available interval.
     *
     * @return to
     */
    @ApiModelProperty(required = true, value = "The start of the available interval.")
    @NotNull


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

