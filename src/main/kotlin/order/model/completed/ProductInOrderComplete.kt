package org.example.order.model.completed

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class ProductInOrderComplete(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idProductInOrder: Long? = null,
    var idProduct: Long? = null,
    var count: Int? = null
)