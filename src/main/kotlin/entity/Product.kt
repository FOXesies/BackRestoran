package org.example.entity

import jakarta.persistence.*
import lombok.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idProduct: Long? = null,
    var name: String,
    var price: Double?,
    var weight: Float?,
    var description: String?,
    @OneToOne(cascade = arrayOf(CascadeType.ALL))
    var category: Category,
    var imageProduct: ByteArray?
)
