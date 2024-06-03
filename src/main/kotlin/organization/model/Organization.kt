package org.example.organization.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.*
import org.example.entity.Category.Category
import org.example.entity.Image

@Entity
data class Organization(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_organization")
    @JsonIgnore
    var idOrganization: Long? = null,
    var name: String,
    var phoneForUser: String,
    var descriptions: String?,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var category: MutableList<Category>,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var locationsAll: MutableList<CityOrganization>,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE])
    @ToString.Exclude
    var idImages: MutableList<Image> = mutableListOf(),
)