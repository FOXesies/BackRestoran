package org.example.order.model.canceled

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class AddressUserCanceled(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idAddress: Long? = null,
    var displayText: String?,
    var lat: Double?,
    var lon: Double?
)