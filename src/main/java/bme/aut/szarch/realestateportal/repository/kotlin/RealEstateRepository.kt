package bme.aut.szarch.realestateportal.repository.kotlin

import bme.aut.szarch.realestateportal.domain.kotlin.RealEstateEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.rest.core.annotation.RepositoryRestResource


@RepositoryRestResource
interface RealEstateRepository :
    JpaRepository<RealEstateEntity, Long>,
    JpaSpecificationExecutor<RealEstateEntity> {

    fun findByUserId(userId: Long, pageable: Pageable): Page<RealEstateEntity>
}


