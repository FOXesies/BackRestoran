package org.example.entity

import jakarta.persistence.*
import lombok.*
import org.springframework.core.io.ClassPathResource
import java.io.InputStream

@Entity
data class Organization(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idOrganization: Long? = null,
    var name: String,
    var address: String,
    var phoneForUser: String,
    var city: String,
    var descriptions: String?,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude // жесть
    var category: List<Category>,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude // жесть
    var images: List<OrganizationImagesProfile>? = null,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude // жесть
    var ratings: List<Rating>? = null
)
