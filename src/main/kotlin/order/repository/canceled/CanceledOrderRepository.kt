package org.example.order.repository.canceled

import org.example.order.model.canceled.CanceledOrder
import org.example.order.model.completed.CompleteOrder
import org.springframework.data.jpa.repository.JpaRepository

interface CanceledOrderRepository: JpaRepository<CanceledOrder, Long> {
    fun findAllByIdUser(idUser: Long): List<CanceledOrder>
}