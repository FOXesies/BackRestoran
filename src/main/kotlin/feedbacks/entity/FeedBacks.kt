package org.example.feedbacks.entity

import jakarta.persistence.*
import org.example.entity.Users_.Users
import org.example.organization.model.Organization
import java.time.LocalDateTime

@Entity
data class FeedBacks(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idFeedback: Long? = null,

    @OneToOne
    var organization: Organization,
    @OneToOne
    var user: Users,

    var rating: Int? = null,
    var comentRating: String? = null,
    var timeComment: LocalDateTime? = null,
)