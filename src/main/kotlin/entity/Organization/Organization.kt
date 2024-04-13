package org.example.entity.Organization

import jakarta.persistence.*
import lombok.*
import org.example.entity.Category.Category
import org.example.entity.OrganizationImagesProfile

@Entity
data class Organization(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idOrganization: Long? = null,
    var name: String,
    var address: String,
    var phoneForUser: String,
    var city: String,
    var idImage: Long,
    var descriptions: String?,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var category: List<Category>,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var images: List<OrganizationImagesProfile>? = null,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var ratings: List<Rating> = listOf(),

    @OneToOne(mappedBy = "organization")
    var user: Test? // Foreign key referencing the User table
)