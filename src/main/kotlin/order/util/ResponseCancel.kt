package org.example.order.util

data class ResponseCancel(
    var idOrder: Long? = null,
    var comment: String? = null
)