package org.example.order.model.active

import jakarta.persistence.*
import lombok.ToString
import org.example.order.model.StatusOrder
import org.example.uuid.model.UUIDCustom

@Entity
data class OrderSelfDelivery (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idOrderSelf: Long? = null,
    var idUser: Long = 1,
    var idOrganization: Long = 1,

    @OneToOne(cascade = [CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH])
    var uuid: UUIDCustom? = null,

    var idLocation: Long? = null,
    var phoneUser: String? = null,
    var toTimeCooling: String? = "now",

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var productOrder: List<ProductInOrder> = mutableListOf(),
    var status: StatusOrder? = null,

    var summ: Double? = null,
    var comment: String = ""
)