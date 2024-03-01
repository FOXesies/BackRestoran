package org.example.entity

import jakarta.persistence.*
import lombok.*

@Entity
data class Organization(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var idOrganization: Long? = null,
    var name: String,
    var address: String,
    var phoneForUser: String,
    var city: String,
    var descriptions: String?,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude // жесть
    var product: List<Product>,
    var imageOrganization: ByteArray?
)
