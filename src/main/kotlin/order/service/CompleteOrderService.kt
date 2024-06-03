package org.example.order.service

import org.example.order.model.completed.CompleteOrder
import org.example.order.model.completed.CompleteOrderSelf
import org.example.order.repository.completed.CompleteOrderRepository
import org.example.order.repository.completed.CompleteOrderSelfRepository
import org.example.order.repository.active.OrderRepository
import org.example.order.repository.active.OrderSelfRepository
import org.example.repository.BasicUserRepository
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
        val user = userRepository.findById(userId).get()
        return completeOrderSelf.findAllByUser(user)
    }
    fun getOrdersByUser(userId: Long): List<CompleteOrder> {
        val user = userRepository.findById(userId).get()
        return completeOrder.findAllByUser(user)
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
    private lateinit var userRepository: BasicUserRepository
    @Autowired
    private lateinit var repository: OrderRepository
    @Autowired
    private lateinit var repositorySelf: OrderSelfRepository
    @Autowired
    private lateinit var completeOrder: CompleteOrderRepository
    @Autowired
    private lateinit var completeOrderSelf: CompleteOrderSelfRepository
}