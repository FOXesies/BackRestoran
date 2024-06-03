package org.example.order.service

import org.example.order.model.active.OrderCustomer
import org.example.order.model.active.OrderSelfDelivery
import org.example.order.repository.active.OrderRepository
import org.example.order.repository.active.OrderSelfRepository
import org.example.basket.service.BasketService
import org.example.repository.BasicUserRepository
import org.example.utils.MapperUtils
import org.example.uuid.model.UUIDCustom
import org.example.uuid.repository.UUIDRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService {
    fun findOrderById(idOrder: Long): OrderCustomer {
        return repository.findById(idOrder).get()
    }
    fun sendOrder(order: OrderCustomer) {
        val products = MapperUtils.mapProductBasketInOrder(basketService.getBasketByUserId(order.user.profileUUID!!).get().productsPick)
        order.productOrder = products
        order.uuid = UUIDCustom()
        repository.save(order)
    }

    fun getOrderById(idOrder: Long): OrderCustomer {
        return repository.findById(idOrder).get()
    }
    fun getOrders(userId: Long): List<OrderCustomer> {
        val user = userRepository.findById(userId).get()
        return repository.findAllByUser(user)
    }
    fun sendOrderSelf(order: OrderSelfDelivery) {
        val products = MapperUtils.mapProductBasketInOrder(basketService.getBasketByUserId(order.user.profileUUID!!).get().productsPick)
        order.productOrder = products
        order.uuid = UUIDCustom()
        repositorySelf.save(order)
    }

    fun getOrdeSelfrById(idOrder: Long): OrderSelfDelivery {
        return repositorySelf.findById(idOrder).get()
    }
    fun getOrdersSelf(userId: Long): List<OrderSelfDelivery> {
        val user = userRepository.findById(userId).get()
        return repositorySelf.findAllByUser(user)
    }

    @Autowired
    private lateinit var userRepository: BasicUserRepository
    @Autowired
    private lateinit var repository: OrderRepository
    @Autowired
    private lateinit var repositorySelf: OrderSelfRepository
    @Autowired
    private lateinit var basketService: BasketService
    @Autowired
    private lateinit var repositoryUUID: UUIDRepository

}