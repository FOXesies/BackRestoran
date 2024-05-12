package org.example.order.model

import jakarta.persistence.*

@Entity
data class FeedBacks(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idFeedback: Long? = null,

    var rating: Float? = null,
    var comentRating: String? = null
)