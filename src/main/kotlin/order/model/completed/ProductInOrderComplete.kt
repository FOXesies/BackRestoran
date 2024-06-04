package org.example.order.model.completed

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import org.example.products.entity.Product

@Entity
data class ProductInOrderComplete(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idProductInOrder: Long? = null,
    @OneToOne
    var product: Product? = null,
    var count: Int? = null
)