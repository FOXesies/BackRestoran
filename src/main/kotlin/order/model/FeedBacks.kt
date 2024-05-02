package org.example.order.model

import jakarta.persistence.*
import org.example.entity.Organization.Organization

@Entity
data class FeedBacks(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idFeedback: Long? = null,

    var rating: Float? = null,
    var comentRating: String? = null
)