package org.example.basket.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import org.example.products.entity.Product

@Entity
data class ProductBasket(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var idBsaket: Long? = null,
    @OneToOne
    var product: Product,
    var count: Int = 0
)