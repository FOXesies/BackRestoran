package org.example.organization_city.model

import jakarta.persistence.*
import lombok.ToString

@Entity
data class CityOrganization(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idCity: Long? = null,
    @Column(unique = true)
    var nameCity: String? = null,
)
