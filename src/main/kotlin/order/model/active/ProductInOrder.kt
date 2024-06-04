package org.example.order.model.active

import jakarta.persistence.*
import org.example.products.entity.Product

@Entity
data class ProductInOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idProductInOrder: Long? = null,
    @OneToOne
    var product: Product? = null,
    var count: Int? = null
)
