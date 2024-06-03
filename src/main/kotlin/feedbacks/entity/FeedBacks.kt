package org.example.feedbacks.entity

import jakarta.persistence.*
import org.example.entity.Users_.Users
import org.example.organization.model.Organization
import org.example.uuid.model.UUIDCustom

@Entity
data class FeedBacks(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idFeedback: Long? = null,

    @OneToOne
    var organization: Organization,
    @OneToOne
    var uuidCustom: UUIDCustom,
    @OneToOne
    var user: Users,

    var rating: Float? = null,
    var comentRating: String? = null
)