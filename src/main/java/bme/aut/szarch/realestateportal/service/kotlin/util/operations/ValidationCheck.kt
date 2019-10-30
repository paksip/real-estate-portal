package bme.aut.szarch.realestateportal.service.kotlin.util.operations


fun checkAuthorization(relatedUserId: Long?, userId: Long) = check(userId == relatedUserId) { "User not Authorized" }
