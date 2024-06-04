package org.example.organization_city.model

import jakarta.persistence.*
import lombok.ToString

@Entity
data class LocationOrganization(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idLocation: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE])
    @ToString.Exclude
    var city: CityOrganization,
    val address: String? = null,
    val lat: Double? = null,
    val lon: Double? = null
)
