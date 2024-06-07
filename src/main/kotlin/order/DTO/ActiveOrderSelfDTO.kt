package org.example.order.DTO

data class ActiveOrderSelfDTO (
    var idUser: Long? = null,
    var idLocation: Long? = null,

    var phoneUser: String? = null,
    var fromTimeCooking: String?,
    var toTimeCooking: String?,

    var summ: Double? = null,
    var comment: String = ""
)