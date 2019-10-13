package bme.aut.szarch.realestateportal.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Location
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-13T16:27:14.871602200+02:00[Europe/Berlin]")

public class Location {
    @JsonProperty("lat")
    private BigDecimal lat;

    @JsonProperty("lon")
    private BigDecimal lon;

    @JsonProperty("locationName")
    private String locationName;

    public Location lat(BigDecimal lat) {
        this.lat = lat;
        return this;
    }

    /**
     * Get lat
     *
     * @return lat
     */
    @ApiModelProperty(value = "")

    @Valid

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public Location lon(BigDecimal lon) {
        this.lon = lon;
        return this;
    }

    /**
     * Get lon
     *
     * @return lon
     */
    @ApiModelProperty(value = "")

    @Valid

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public Location locationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    /**
     * Get locationName
     *
     * @return locationName
     */
    @ApiModelProperty(value = "")


    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        return Objects.equals(this.lat, location.lat) &&
            Objects.equals(this.lon, location.lon) &&
            Objects.equals(this.locationName, location.locationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon, locationName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Location {\n");

        sb.append("    lat: ").append(toIndentedString(lat)).append("\n");
        sb.append("    lon: ").append(toIndentedString(lon)).append("\n");
        sb.append("    locationName: ").append(toIndentedString(locationName)).append("\n");
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

