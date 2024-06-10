package org.example.order.DTO.sen_response

data class FeedbackCreateDTO(
    val idOrder: Long,
    val rating: Int,
    val comment: String?
)