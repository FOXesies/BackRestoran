package org.example.order.model.completed

import jakarta.persistence.*
import lombok.ToString
import org.example.entity.Users_.Users
import org.example.order.model.AddressUser
import org.example.feedbacks.entity.FeedBacks
import org.example.organization.model.Organization

@Entity
data class CompleteOrder (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderId: Long = 0,

    @OneToOne
    var user: Users,
    @OneToOne
    var organization: Organization,

    var uuid: Long? = null,

    @OneToOne(cascade = [CascadeType.ALL])
    var addressUser: AddressUser? = null,
    var idLocation: Long? = null,
    var phoneUser: String? = null,
    var fromTimeDelivery: String? = "now",
    var toTimeDelivery: String? = "now",

    @OneToOne(cascade = [CascadeType.ALL])
    var feedBacks: FeedBacks? = null,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var productOrder: List<ProductInOrderComplete> = mutableListOf(),

    var podezd: String? = null,
    var homephome: String? = null,
    var appartamnet: String? = null,
    var level: String? = null,

    var summ: Double? = null,
    var comment: String? = null
)