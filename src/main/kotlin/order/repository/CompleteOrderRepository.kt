package org.example.order.repository

import org.example.order.model.CompleteOrder
import org.example.order.model.OrderSelfDelivery
import org.springframework.data.jpa.repository.JpaRepository

interface CompleteOrderRepository: JpaRepository<CompleteOrder, Long> {
    fun findAllByIdUser(idUser: Long): List<CompleteOrder>
}