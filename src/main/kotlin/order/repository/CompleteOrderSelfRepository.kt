package org.example.order.repository

import org.example.order.model.CompleteOrderSelf
import org.example.order.model.OrderSelfDelivery
import org.springframework.data.jpa.repository.JpaRepository

interface CompleteOrderSelfRepository: JpaRepository<CompleteOrderSelf, Long> {
    fun findAllByIdUser(idUser: Long): List<CompleteOrderSelf>
}