package org.example.order.DTO

import org.example.order.model.AddressUser


data class ActiveOrderDTO (
    var idUser: Long? = null,

    var addressUser: AddressUser? = null,
    var phoneUser: String? = null,
    var fromTimeDelivery: String?,
    var toTimeDelivery: String?,

    var podezd: String = "",
    var homephome: String = "",
    var appartamnet: String = "",
    var level: String = "",

    var summ: Double? = null,
    var comment: String = ""
)