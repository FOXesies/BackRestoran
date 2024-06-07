package org.example.order.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class AddressUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idAddress: Long? = null,
    var displayText: String? = null,
    var lat: Double? = null,
    var lon: Double? = null,

    var podezd: String? = null,
    var homephome: String? = null,
    var appartamnet: String? = null,
    var level: String? = null,
)