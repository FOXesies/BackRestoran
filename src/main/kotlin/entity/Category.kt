package org.example.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.*
import java.io.InputStream

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
