package org.example.entity.Organization

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.*
import org.example.entity.Category.Category
import org.example.entity.OrganizationImagesProfile

@Entity
data class Organization(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_organization")
    @JsonIgnore
    var idOrganization: Long? = null,
    var name: String,
    var phoneForUser: String,
    var idImage: Long,
    var descriptions: String?,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var category: List<Category>,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var locationsAll: List<CityOrganization>,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var images: List<OrganizationImagesProfile>? = null,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var ratings: List<Rating> = listOf()
)