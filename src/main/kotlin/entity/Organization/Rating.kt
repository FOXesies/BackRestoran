package org.example.entity.Organization

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Rating(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idRating: Long? = null,
    var rating: Int,
    var context: String? = null,
    var responseOrg: String? = null
)