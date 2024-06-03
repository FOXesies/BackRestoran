package org.example.entity.Users_.DTO

import jakarta.persistence.*
import lombok.ToString
import org.example.entity.Users.ERole
import org.example.favorite.entity.FavoriteProduct
import java.time.LocalDateTime

data class UserResponse(
    var profileUUID: Long,
    var city: String,
    var name: String,
    var favoriteProducts: MutableList<FavoriteProduct>,
    var dateOfCreate: LocalDateTime,
    var phone: String?,
    var roles: Set<ERole>
)