package org.example.products_category.entity

import jakarta.persistence.*
import lombok.*
import org.example.entity.Image
import org.example.products.entity.Product

@Entity
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCategory: Long? = null,
    @Column(unique = true)
    var name: String
) {
}
