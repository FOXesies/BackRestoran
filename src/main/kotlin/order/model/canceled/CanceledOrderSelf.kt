package org.example.order.model.canceled

import jakarta.persistence.*
import lombok.ToString
import org.example.order.model.FeedBacks
import org.example.order.model.completed.ProductInOrderComplete
import org.example.order.model.StatusOrder
import org.example.uuid.model.UUIDCustom

@Entity
data class CanceledOrderSelf (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idOrderSelf: Long? = null,
    var idUser: Long = 1,
    var idOrganization: String = "",
    var canceled_comment: String? = null,

    var uuid: Long? = null,

    var idLocation: Long? = null,
    var phoneUser: String? = null,
    var toTimeCooling: String? = "now",
    var CanceledTime: String? = "now",

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var productOrder: List<ProductInOrderComplete> = mutableListOf(),
    val status: StatusOrder? = null,

    var summ: Double? = null,
    var comment: String = "",

    )