package org.example.order.repository

import org.example.order.model.OrderSelfDelivery
import org.springframework.data.jpa.repository.JpaRepository

interface OrderSelfRepository: JpaRepository<OrderSelfDelivery, Long> {
    fun findAllByIdUser(idUser: Long): List<OrderSelfDelivery>
}