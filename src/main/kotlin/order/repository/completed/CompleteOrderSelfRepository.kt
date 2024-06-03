package org.example.order.repository.completed

import org.example.entity.Users_.Users
import org.example.order.model.completed.CompleteOrderSelf
import org.springframework.data.jpa.repository.JpaRepository

interface CompleteOrderSelfRepository: JpaRepository<CompleteOrderSelf, Long> {
    fun findAllByUser(user: Users): List<CompleteOrderSelf>
}