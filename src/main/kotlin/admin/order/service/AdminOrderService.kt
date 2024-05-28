package org.example.admin.order.service

import org.example.admin.order.model.AdminStatusResponse
import org.example.order.model.active.OrderCustomer
import org.example.order.model.active.OrderSelfDelivery
import org.example.order.repository.active.OrderRepository
import org.example.order.repository.active.OrderSelfRepository
import org.example.order.repository.canceled.CanceledOrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AdminOrderService {

    @Autowired
    private lateinit var orderRepository: OrderRepository
    @Autowired
    private lateinit var orderSelfRepository: OrderSelfRepository

    fun switchStatusSelf(statusRespone: AdminStatusResponse){
        val order = orderSelfRepository.findById(statusRespone.id).get()
        order.status = statusRespone.status
        orderSelfRepository.save(order)
    }

    fun switchStatus(statusRespone: AdminStatusResponse){
        val order = orderRepository.findById(statusRespone.id).get()
        order.status = statusRespone.status
        orderRepository.save(order)
    }

    fun getOrders(idOrg: Long): List<OrderCustomer>{
        return orderRepository.findAllByIdUser(idOrg)
    }
    fun getOrdersSelf(idOrg: Long): List<OrderSelfDelivery>{
        return orderSelfRepository.findAllByIdUser(idOrg)
    }

}