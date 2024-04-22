package org.example.entity.Users_

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var profileUUID: Long? = null,
    var city: String? = null,
    var email: String? = null,
    var name: String? = null,
    var address: String? = null,
    var phone: String? = null,
)