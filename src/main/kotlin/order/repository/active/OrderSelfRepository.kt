package org.example.order.repository.active

import org.example.entity.Users_.Users
import org.example.order.model.StatusOrder
import org.example.order.model.active.OrderCustomer
import org.example.order.model.active.OrderSelfDelivery
import org.example.organization.model.Organization
import org.springframework.data.jpa.repository.JpaRepository

interface OrderSelfRepository: JpaRepository<OrderSelfDelivery, Long> {
    fun findAllByOrganization(organization: Organization): List<OrderSelfDelivery>
    fun findAllByUser(user: Users): List<OrderSelfDelivery>
}