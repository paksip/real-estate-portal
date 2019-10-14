package bme.aut.szarch.realestateportal.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Reservation
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-13T16:27:14.871602200+02:00[Europe/Berlin]")

public class Reservation {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("from")
    private OffsetDateTime from;

    @JsonProperty("to")
    private OffsetDateTime to;

    public Reservation id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
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

    public Reservation from(OffsetDateTime from) {
        this.from = from;
        return this;
    }

    /**
     * The start of the reservation interval.
     *
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

    public Reservation to(OffsetDateTime to) {
        this.to = to;
        return this;
    }

    /**
     * The end of the reservation interval.
     *
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reservation reservation = (Reservation) o;
        return Objects.equals(this.id, reservation.id) &&
            Objects.equals(this.from, reservation.from) &&
            Objects.equals(this.to, reservation.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Reservation {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

