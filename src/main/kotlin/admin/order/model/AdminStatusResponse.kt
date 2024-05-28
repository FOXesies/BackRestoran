package org.example.admin.order.model

import org.example.order.model.StatusOrder

data class AdminStatusResponse (
    val id: Long,
    val status: StatusOrder
)