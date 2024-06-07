package org.example.products.entity

import jakarta.persistence.*
import lombok.*
import org.example.entity.Image
import org.example.products_category.entity.Category

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idProduct: Long? = null,
    var name: String,
    var price: Double?,
    var weight: Float?,
    @Column(length = 1024)
    var description: String?,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE])
    @ToString.Exclude
    var category: Category,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var images: MutableList<Image> = mutableListOf()
)
