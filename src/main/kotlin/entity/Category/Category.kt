package org.example.entity.Category

import jakarta.persistence.*
import lombok.*
import org.example.entity.OrganizationImagesProfile
import org.example.entity.Product.Product

@Entity
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCategory: Long? = null,
    var name: String,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude // жесть
    var images: List<OrganizationImagesProfile>? = null,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var product: List<Product>
) {
}
