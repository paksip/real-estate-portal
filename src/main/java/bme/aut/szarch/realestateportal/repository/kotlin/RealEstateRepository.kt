package bme.aut.szarch.realestateportal.repository.kotlin

import bme.aut.szarch.realestateportal.domain.kotlin.RealEstateEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.lang.Nullable
import org.springframework.stereotype.Repository

@Repository
interface RealEstateRepository :
    JpaRepository<RealEstateEntity, Long>,
    JpaSpecificationExecutor<RealEstateEntity> {

    fun findByUserId(userId: Long, pageable: Pageable): Page<RealEstateEntity>
}


