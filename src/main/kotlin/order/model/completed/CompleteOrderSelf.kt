package org.example.order.model.completed

import jakarta.persistence.*
import lombok.ToString
import org.example.entity.Users_.Users
import org.example.feedbacks.entity.FeedBacks
import org.example.order.model.StatusOrder
import org.example.organization.model.Organization

@Entity
data class CompleteOrderSelf (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idOrderSelf: Long? = null,

    @OneToOne
    var user: Users,
    @OneToOne
    var organization: Organization,

    var uuid: Long? = null,

    var phoneUser: String? = null,
    var fromTimeCooking: String = "now",
    var toTimeCooking: String? = "now",

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var productOrder: List<ProductInOrderComplete> = mutableListOf(),

    var summ: Double? = null,
    var comment: String = "",

    @OneToOne(cascade = [CascadeType.ALL])
    var feedBacks: FeedBacks? = null,

    )