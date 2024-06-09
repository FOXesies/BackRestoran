package org.example.order.DTO.sen_response

data class SendCanceledOrderPreview (
    val comment: String? = null,
    val timeCandeled: String? = null,
    val orderPreview: SendOrderPreview? = null
)