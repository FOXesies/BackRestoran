package org.example.favorite.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import org.example.products.entity.Product

@Entity
data class FavoriteProduct (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idFavorite: Long? = null,
    @OneToOne
    val product: Product,
)