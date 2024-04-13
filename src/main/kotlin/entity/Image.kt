package org.example.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Image(
    @Id
    var id: Long? = null,
    var path: String
)