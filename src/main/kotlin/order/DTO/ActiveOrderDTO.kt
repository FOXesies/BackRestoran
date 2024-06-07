package org.example.order.DTO

import org.example.order.model.AddressUser


data class ActiveOrderDTO (
    var idUser: Long? = null,

    var addressUser: AddressUser? = null,
    var phoneUser: String? = null,
    var fromTimeDelivery: String?,
    var toTimeDelivery: String?,
    var isSelf: Boolean = false,
    var idLocation: Long? = null,

    var summ: Double? = null,
    var comment: String = ""
)