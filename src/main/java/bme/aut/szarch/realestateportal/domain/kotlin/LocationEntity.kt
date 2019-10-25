package bme.aut.szarch.realestateportal.domain.kotlin

import javax.persistence.Embeddable


@Embeddable
data class LocationEntity(
    val lat: Int,
    val lon: Int,
    val locationName: String
)
