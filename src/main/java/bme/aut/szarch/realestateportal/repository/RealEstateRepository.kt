package bme.aut.szarch.realestateportal.repository

import bme.aut.szarch.realestateportal.domain.RealEstateEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RealEstateRepository : JpaRepository<RealEstateEntity, Long> {
    fun findByUserId(userId: Long): List<RealEstateEntity>
    fun findByIdAndUserId(id: Long, userId: Long): RealEstateEntity?
}


