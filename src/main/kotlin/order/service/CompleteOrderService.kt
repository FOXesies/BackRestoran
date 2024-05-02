package org.example.order.service

import org.example.order.model.CompleteOrder
import org.example.order.model.CompleteOrderSelf
import org.example.order.model.OrderSelfDelivery
import org.example.order.repository.CompleteOrderRepository
import org.example.order.repository.CompleteOrderSelfRepository
import org.example.order.repository.OrderRepository
import org.example.order.repository.OrderSelfRepository
import org.example.service.BasketService
import org.example.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CompleteOrderService() {
    fun getOrderById(idOrder: Long): CompleteOrder {
        return completeOrder.findById(idOrder).get()
    }
    fun getOrderSelfById(idOrder: Long): CompleteOrderSelf {
        return completeOrderSelf.findById(idOrder).get()
    }
    fun getOrdersSelfByUser(userId: Long): List<CompleteOrderSelf> {
        return completeOrderSelf.findAllByIdUser(userId)
    }
    fun getOrdersByUser(userId: Long): List<CompleteOrder> {
        return completeOrder.findAllByIdUser(userId)
    }
    fun getOrdersSelf(userId: Long): List<CompleteOrderSelf> {
        return completeOrderSelf.findAllByIdUser(userId)
    }
    fun getOrders(userId: Long): List<CompleteOrder> {
        return completeOrder.findAllByIdUser(userId)
    }

    fun sendOrderSelf(idOrder: Long) {
        val order = repositorySelf.findById(idOrder).get()
        completeOrderSelf.save(MapperUtils.mapOrderInCompleteSelf(order))
        repositorySelf.deleteById(idOrder)
    }

    fun sendOrder(idOrder: Long) {
        val order = repository.findById(idOrder).get()
        completeOrder.save(MapperUtils.mapOrderInComplete(order))
        repository.deleteById(idOrder)
    }

    @Autowired
    private lateinit var repository: OrderRepository
    @Autowired
    private lateinit var repositorySelf: OrderSelfRepository
    @Autowired
    private lateinit var completeOrder: CompleteOrderRepository
    @Autowired
    private lateinit var completeOrderSelf: CompleteOrderSelfRepository
}