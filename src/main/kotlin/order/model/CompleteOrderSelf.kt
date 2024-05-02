package org.example.order.model

import jakarta.persistence.*
import lombok.ToString

@Entity
data class CompleteOrderSelf (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idOrderSelf: Long? = null,
    var idUser: Long = 1,
    var idOrganization: String = "",

    var idLocation: Long? = null,
    var phoneUser: String? = null,
    var toTimeCooling: String? = "now",

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var productOrder: List<ProductInOrderComplete> = mutableListOf(),
    val status: StatusOrder? = null,

    var summ: Double? = null,
    var comment: String = "",

    @OneToOne(cascade = [CascadeType.ALL])
    var feedBacks: FeedBacks? = null,

)