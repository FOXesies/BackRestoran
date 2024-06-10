package org.example.feedbacks.entity

import org.dom4j.Comment

data class FeedbacksDTO(
    val idFeedback: Long,
    val rating: Int,
    val comment: String? = null,
    val userName: String,
    val dateCreate: String
)