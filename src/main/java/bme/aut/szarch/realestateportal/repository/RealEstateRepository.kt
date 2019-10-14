package bme.aut.szarch.realestateportal.repository

import bme.aut.szarch.realestateportal.domain.RealEstateEntity
import bme.aut.szarch.realestateportal.repository.realstatespecification.RealEstateSpecification
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface RealEstateRepository :
    JpaRepository<RealEstateEntity, Long>,
    JpaSpecificationExecutor<RealEstateEntity> {

    fun findByUserId(userId: Long): List<RealEstateEntity>
    fun findByIdAndUserId(id: Long, userId: Long): RealEstateEntity?

    override fun findAll(pageable: Pageable): Page<RealEstateEntity>

}


