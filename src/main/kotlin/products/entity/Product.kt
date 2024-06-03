package org.example.products.entity

import jakarta.persistence.*
import lombok.*
import org.example.entity.Image

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idProduct: Long? = null,
    var name: String,
    var price: Double?,
    var weight: Float?,
    var description: String?,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var images: MutableList<Image> = mutableListOf()
)
