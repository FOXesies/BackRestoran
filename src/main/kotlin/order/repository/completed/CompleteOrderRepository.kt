package org.example.order.repository.completed

import org.example.order.model.completed.CompleteOrder
import org.springframework.data.jpa.repository.JpaRepository

interface CompleteOrderRepository: JpaRepository<CompleteOrder, Long> {
    fun findAllByIdUser(idUser: Long): List<CompleteOrder>
}