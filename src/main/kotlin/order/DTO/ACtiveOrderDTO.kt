package org.example.order.DTO

import org.example.order.model.AddressUser
import org.example.order.model.StatusOrder
import org.example.order.model.active.ProductInOrder
import org.example.uuid.model.UUIDCustom
import java.time.LocalDateTime


data class ActiveOrderDTO (
    var idUser: Long? = null,

    var addressUser: AddressUser? = null,
    var idLocation: Long? = null,
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