package org.example.order.model.canceled

import jakarta.persistence.*
import lombok.ToString
import org.example.entity.Users_.Users
import org.example.order.model.AddressUser
import org.example.order.model.completed.ProductInOrderComplete
import org.example.organization.model.Organization

@Entity
data class CanceledOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderId: Long = 0,
    var canceled_comment: String? = null,

    var idDriver: Long? = null,
    @OneToOne
    var user: Users,
    @OneToOne
    var organization: Organization,

    var uuid: Long? = null,

    @OneToOne(cascade = [CascadeType.ALL])
    var addressUser: AddressUser? = null,
    var idLocation: Long? = null,
    var phoneUser: String? = null,
    var toTimeDelivery: String? = "now",
    var CanceledTime: String? = "now",

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