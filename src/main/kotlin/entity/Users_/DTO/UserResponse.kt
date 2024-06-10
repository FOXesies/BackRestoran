package org.example.entity.Users_.DTO

import jakarta.persistence.*
import lombok.ToString
import org.example.entity.Users.ERole
import org.example.favorite.entity.FavoriteProduct
import java.time.LocalDateTime

data class UserResponse(
    var profileUUID: Long? = null,
    var city: String? = null,
    var name: String? = null,
    var favoriteProducts: MutableList<FavoriteProduct> = mutableListOf(),
    var dateOfCreate: LocalDateTime? = null,
    var phone: String?,
    var roles: Set<ERole>? = setOf(),
)