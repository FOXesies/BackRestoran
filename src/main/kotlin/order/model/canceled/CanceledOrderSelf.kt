package org.example.order.model.canceled

import jakarta.persistence.*
import lombok.ToString
import org.example.entity.Users_.Users
import org.example.order.model.completed.ProductInOrderComplete
import org.example.order.model.StatusOrder
import org.example.organization.model.Organization

@Entity
data class CanceledOrderSelf (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idOrderSelf: Long? = null,

    @OneToOne
    var user: Users,
    @OneToOne
    var organization: Organization,
    var canceled_comment: String? = null,

    var uuid: Long? = null,

    var phoneUser: String? = null,
    var fromTimeCooking: String? = "now",
    var toTimeCooking: String? = "now",
    var CanceledTime: String? = "now",

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var productOrder: List<ProductInOrderComplete> = mutableListOf(),
    val status: StatusOrder? = null,

    var summ: Double? = null,
    var comment: String = "",

    )