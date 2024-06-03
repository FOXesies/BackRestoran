package org.example.order.repository.completed

import org.example.entity.Users_.Users
import org.example.order.model.completed.CompleteOrder
import org.springframework.data.jpa.repository.JpaRepository

interface CompleteOrderRepository: JpaRepository<CompleteOrder, Long> {
    fun findAllByUser(user: Users): List<CompleteOrder>
}