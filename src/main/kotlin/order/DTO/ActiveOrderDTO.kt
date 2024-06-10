package org.example.order.DTO

import org.example.order.model.AddressUser
import org.example.order.model.StatusPayment


data class ActiveOrderDTO (
    var idUser: Long? = null,

    var addressUser: AddressUser? = null,
    var phoneUser: String? = null,
    var fromTimeDelivery: String?,
    var toTimeDelivery: String?,
    var isSelf: Boolean = false,
    var idLocation: Long? = null,
    var payment: StatusPayment,

    var summ: Double? = null,
    var comment: String = ""
)