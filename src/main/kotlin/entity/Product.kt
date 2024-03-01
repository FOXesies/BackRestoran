package org.example.entity

import jakarta.persistence.*
import lombok.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var idProduct: Long? = null,
    @Column
    private var name: String,
    @Column
    private var price: Double?,
    @Column
    private var weight: Float?,
    @Column
    private var description: String?,
    @OneToOne(cascade = arrayOf(CascadeType.ALL))
    private var category: Category,
    @Column(columnDefinition = "bytea")
    private var imageProduct: ByteArray?
)
