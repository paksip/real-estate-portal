package bme.aut.szarch.realestateportal.service.kotlin.extensions

import java.util.*

fun <T> Optional<T>.orNull(): T? = orElse(null)
