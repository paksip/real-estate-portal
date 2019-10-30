package bme.aut.szarch.realestateportal.domain.kotlin

import bme.aut.szarch.realestateportal.domain.User
import javax.persistence.ManyToOne
import javax.persistence.MappedSuperclass


@MappedSuperclass
abstract class AbstractUserRelatedEntity : AbstractJpaPersistable() {
    @ManyToOne
    lateinit var user: User
}


