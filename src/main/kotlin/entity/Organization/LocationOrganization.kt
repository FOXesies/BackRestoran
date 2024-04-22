package org.example.entity.Organization

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class LocationOrganization(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idLocation: Long? = null,
    val address: String? = null,
    val lat: Double? = null,
    val lon: Double? = null
)
