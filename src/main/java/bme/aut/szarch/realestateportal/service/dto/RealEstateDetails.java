package bme.aut.szarch.realestateportal.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * RealEstateDetails
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-13T16:27:14.871602200+02:00[Europe/Berlin]")

public class RealEstateDetails {
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("location")
    private Location location;

    /**
     * Gets or Sets category
     */
    public enum CategoryEnum {
        FLAT("FLAT"),

        DETACHED_HOUSE("DETACHED_HOUSE"),

        LUXORY_HOUSE("LUXORY_HOUSE"),

        COUNTRY_HOUSE("COUNTRY_HOUSE"),

        PANEL("PANEL");

        private String value;

        CategoryEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static CategoryEnum fromValue(String value) {
            for (CategoryEnum b : CategoryEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    @JsonProperty("category")
    private CategoryEnum category;

    @JsonProperty("spectatorsCount")
    private Long spectatorsCount;

    @JsonProperty("availableReservationTimes")
    @Valid
    private List<AvailableReservationTime> availableReservationTimes = new ArrayList<>();

    @JsonProperty("squareMeter")
    private Integer squareMeter;

    @JsonProperty("price")
    private Integer price;

    @JsonProperty("numberOfRooms")
    private Integer numberOfRooms;

    @JsonProperty("hasBalncony")
    private Boolean hasBalncony;

    @JsonProperty("hasAirCondition")
    private Boolean hasAirCondition;

    @JsonProperty("ownerPhoneNumber")
    private String ownerPhoneNumber;

    @JsonProperty("filePaths")
    @Valid
    private List<String> filePaths = new ArrayList<>();

    @JsonProperty("reservations")
    @Valid
    private List<Reservation> reservations = new ArrayList<>();

    public RealEstateDetails name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
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

    public RealEstateDetails description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get description
     *
     * @return description
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RealEstateDetails location(Location location) {
        this.location = location;
        return this;
    }

    /**
     * Get location
     *
     * @return location
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull

    @Valid

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public RealEstateDetails category(CategoryEnum category) {
        this.category = category;
        return this;
    }

    /**
     * Get category
     *
     * @return category
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull


    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public RealEstateDetails spectatorsCount(Long spectatorsCount) {
        this.spectatorsCount = spectatorsCount;
        return this;
    }

    /**
     * Get spectatorsCount
     *
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

    public RealEstateDetails availableReservationTimes(List<AvailableReservationTime> availableReservationTimes) {
        this.availableReservationTimes = availableReservationTimes;
        return this;
    }

    public RealEstateDetails addAvailableReservationTimesItem(AvailableReservationTime availableReservationTimesItem) {
        this.availableReservationTimes.add(availableReservationTimesItem);
        return this;
    }

    /**
     * Get availableReservationTimes
     *
     * @return availableReservationTimes
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull

    @Valid

    public List<AvailableReservationTime> getAvailableReservationTimes() {
        return availableReservationTimes;
    }

    public void setAvailableReservationTimes(List<AvailableReservationTime> availableReservationTimes) {
        this.availableReservationTimes = availableReservationTimes;
    }

    public RealEstateDetails squareMeter(Integer squareMeter) {
        this.squareMeter = squareMeter;
        return this;
    }

    /**
     * Get squareMeter
     *
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

    public RealEstateDetails price(Integer price) {
        this.price = price;
        return this;
    }

    /**
     * Get price
     *
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

    public RealEstateDetails numberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
        return this;
    }

    /**
     * Get numberOfRooms
     *
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

    public RealEstateDetails hasBalncony(Boolean hasBalncony) {
        this.hasBalncony = hasBalncony;
        return this;
    }

    /**
     * Get hasBalncony
     *
     * @return hasBalncony
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull


    public Boolean getHasBalncony() {
        return hasBalncony;
    }

    public void setHasBalncony(Boolean hasBalncony) {
        this.hasBalncony = hasBalncony;
    }

    public RealEstateDetails hasAirCondition(Boolean hasAirCondition) {
        this.hasAirCondition = hasAirCondition;
        return this;
    }

    /**
     * Get hasAirCondition
     *
     * @return hasAirCondition
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull


    public Boolean getHasAirCondition() {
        return hasAirCondition;
    }

    public void setHasAirCondition(Boolean hasAirCondition) {
        this.hasAirCondition = hasAirCondition;
    }

    public RealEstateDetails ownerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
        return this;
    }

    /**
     * Get ownerPhoneNumber
     *
     * @return ownerPhoneNumber
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull


    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public RealEstateDetails filePaths(List<String> filePaths) {
        this.filePaths = filePaths;
        return this;
    }

    public RealEstateDetails addFilePathsItem(String filePathsItem) {
        this.filePaths.add(filePathsItem);
        return this;
    }

    /**
     * Get filePaths
     *
     * @return filePaths
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull


    public List<String> getFilePaths() {
        return filePaths;
    }

    public void setFilePaths(List<String> filePaths) {
        this.filePaths = filePaths;
    }

    public RealEstateDetails reservations(List<Reservation> reservations) {
        this.reservations = reservations;
        return this;
    }

    public RealEstateDetails addReservationsItem(Reservation reservationsItem) {
        this.reservations.add(reservationsItem);
        return this;
    }

    /**
     * Get reservations
     *
     * @return reservations
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull

    @Valid

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RealEstateDetails realEstateDetails = (RealEstateDetails) o;
        return Objects.equals(this.name, realEstateDetails.name) &&
            Objects.equals(this.description, realEstateDetails.description) &&
            Objects.equals(this.location, realEstateDetails.location) &&
            Objects.equals(this.category, realEstateDetails.category) &&
            Objects.equals(this.spectatorsCount, realEstateDetails.spectatorsCount) &&
            Objects.equals(this.availableReservationTimes, realEstateDetails.availableReservationTimes) &&
            Objects.equals(this.squareMeter, realEstateDetails.squareMeter) &&
            Objects.equals(this.price, realEstateDetails.price) &&
            Objects.equals(this.numberOfRooms, realEstateDetails.numberOfRooms) &&
            Objects.equals(this.hasBalncony, realEstateDetails.hasBalncony) &&
            Objects.equals(this.hasAirCondition, realEstateDetails.hasAirCondition) &&
            Objects.equals(this.ownerPhoneNumber, realEstateDetails.ownerPhoneNumber) &&
            Objects.equals(this.filePaths, realEstateDetails.filePaths) &&
            Objects.equals(this.reservations, realEstateDetails.reservations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, location, category, spectatorsCount, availableReservationTimes, squareMeter, price, numberOfRooms, hasBalncony, hasAirCondition, ownerPhoneNumber, filePaths, reservations);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RealEstateDetails {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    location: ").append(toIndentedString(location)).append("\n");
        sb.append("    category: ").append(toIndentedString(category)).append("\n");
        sb.append("    spectatorsCount: ").append(toIndentedString(spectatorsCount)).append("\n");
        sb.append("    availableReservationTimes: ").append(toIndentedString(availableReservationTimes)).append("\n");
        sb.append("    squareMeter: ").append(toIndentedString(squareMeter)).append("\n");
        sb.append("    price: ").append(toIndentedString(price)).append("\n");
        sb.append("    numberOfRooms: ").append(toIndentedString(numberOfRooms)).append("\n");
        sb.append("    hasBalncony: ").append(toIndentedString(hasBalncony)).append("\n");
        sb.append("    hasAirCondition: ").append(toIndentedString(hasAirCondition)).append("\n");
        sb.append("    ownerPhoneNumber: ").append(toIndentedString(ownerPhoneNumber)).append("\n");
        sb.append("    filePaths: ").append(toIndentedString(filePaths)).append("\n");
        sb.append("    reservations: ").append(toIndentedString(reservations)).append("\n");
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

