package org.example.order.repository.active

import org.example.order.model.active.OrderCustomer
import org.example.order.model.active.OrderSelfDelivery
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<OrderCustomer, Long>{
    fun findAllByIdOrganization(idOrganization: Long): List<OrderSelfDelivery>
    fun findAllByIdUser(idUser: Long): List<OrderCustomer>
}