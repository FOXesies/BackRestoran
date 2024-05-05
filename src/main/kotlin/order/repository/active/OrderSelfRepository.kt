package org.example.order.repository.active

import org.example.order.model.active.OrderSelfDelivery
import org.springframework.data.jpa.repository.JpaRepository

interface OrderSelfRepository: JpaRepository<OrderSelfDelivery, Long> {
    fun findAllByIdUser(idUser: Long): List<OrderSelfDelivery>
}