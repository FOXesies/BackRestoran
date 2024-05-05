package org.example.order.repository.canceled

import org.example.order.model.canceled.CanceledOrderSelf
import org.example.order.model.completed.CompleteOrder
import org.springframework.data.jpa.repository.JpaRepository

interface CanceledOrderSelfRepository: JpaRepository<CanceledOrderSelf, Long> {
    fun findAllByIdUser(idUser: Long): List<CanceledOrderSelf>
}