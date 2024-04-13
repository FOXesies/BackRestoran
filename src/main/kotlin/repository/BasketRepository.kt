package org.example.repository

import org.example.entity.Orders.Order
import org.springframework.data.jpa.repository.JpaRepository

interface BasketRepository: JpaRepository<Order, Long> {
}