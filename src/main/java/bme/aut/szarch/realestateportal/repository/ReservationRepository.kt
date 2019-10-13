package bme.aut.szarch.realestateportal.repository

import bme.aut.szarch.realestateportal.domain.ReservationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface ReservationRepository : JpaRepository<ReservationEntity, Long> {
    fun findByRealEstateId(realEstateId: Long): List<ReservationEntity>
    fun findByIdAndRealEstateId(id: Long, realEstateId: Long): ReservationEntity?
}
