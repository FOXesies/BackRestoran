package org.example.entity.Basket

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class ProductBasket(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var idBsaket: Long? = null,
    @Column(name = "product_id_pick")
    var productId: Long,
    var count: Int = 0
)