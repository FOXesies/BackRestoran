package org.example.order.repository.canceled

import org.example.entity.Users_.Users
import org.example.order.model.canceled.CanceledOrder
import org.example.order.model.completed.CompleteOrder
import org.springframework.data.jpa.repository.JpaRepository

interface CanceledOrderRepository: JpaRepository<CanceledOrder, Long> {
    fun findAllByUser(user: Users): List<CanceledOrder>
}