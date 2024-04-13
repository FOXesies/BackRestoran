package org.example.entity.Favorite

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import javax.persistence.Entity
import javax.persistence.Id
@Entity
data class FavoriteProduct(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idFavorite: Long? = null,
    var idProduct: Long? = null
)
