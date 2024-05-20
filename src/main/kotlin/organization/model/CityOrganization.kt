package org.example.organization.model

import jakarta.persistence.*
import lombok.ToString

@Entity
data class CityOrganization(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idCity: Long? = null,
    var nameCity: String? = null,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var locationInCity: MutableList<LocationOrganization> = mutableListOf()
)
