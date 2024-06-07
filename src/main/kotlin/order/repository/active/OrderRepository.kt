package org.example.order.repository.active

import org.example.entity.Users_.Users
import org.example.order.model.active.OrderCustomer
import org.example.organization.model.Organization
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<OrderCustomer, Long>{
    fun findAllByOrganization(organization: Organization): List<OrderCustomer>
    fun findAllByUser(user: Users): List<OrderCustomer>
}