package org.example.order.service

import org.example.order.model.OrderCustomer
import org.example.order.model.OrderSelfDelivery
import org.example.order.repository.OrderRepository
import org.example.order.repository.OrderSelfRepository
import org.example.service.BasketService
import org.example.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Service

@Service
class OrderService {
    fun findOrderById(idOrder: Long): OrderCustomer {
        return repository.findById(idOrder).get()
    }
    fun sendOrder(order: OrderCustomer) {
        val products = MapperUtils.mapProductBasketInOrder(basketService.getBasketByUserId(order.idUser!!).get().productsPick)
        order.productOrder = products
        repository.save(order)
    }

    fun getOrderById(idOrder: Long): OrderCustomer {
        return repository.findById(idOrder).get()
    }
    fun getOrders(userId: Long): List<OrderCustomer> {
        return repository.findAllByIdUser(userId)
    }
    fun sendOrderSelf(order: OrderSelfDelivery) {
        val products = MapperUtils.mapProductBasketInOrder(basketService.getBasketByUserId(order.idUser).get().productsPick)
        order.productOrder = products
        repositorySelf.save(order)
    }

    fun getOrdeSelfrById(idOrder: Long): OrderSelfDelivery {
        return repositorySelf.findById(idOrder).get()
    }
    fun getOrdersSelf(userId: Long): List<OrderSelfDelivery> {
        return repositorySelf.findAllByIdUser(userId)
    }

    @Autowired
    private lateinit var repository: OrderRepository
    @Autowired
    private lateinit var repositorySelf: OrderSelfRepository
    @Autowired
    private lateinit var basketService: BasketService

}