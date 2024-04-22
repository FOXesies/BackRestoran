package org.example.order.repository

import org.example.order.model.OrderCustomer
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<OrderCustomer, Long>{
}