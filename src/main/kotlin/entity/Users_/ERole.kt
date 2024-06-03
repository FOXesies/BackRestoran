package org.example.entity.Users

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class ERole(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var idRole: Long? = null,
        var nameRole: String
)