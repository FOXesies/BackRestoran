package org.example.entity.Orders

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.example.entity.Product.Product

@Entity
data class ProductInOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var idProduct: Long,
    var count: Int = 1,
)