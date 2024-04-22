package org.example.order.service

import org.example.order.model.OrderCustomer
import org.example.order.repository.OrderRepository
import org.example.service.BasketService
import org.example.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Service

@Service
class OrderService {
    fun sendOrder(order: OrderCustomer) {
        val products = MapperUtils.mapProductBasketInOrder(basketService.getBasketByUserId(order.idUser!!).get().productsPick)
        order.productOrder = products
        repository.save(order)
    }
    fun findOrderById(idOrder: Long): OrderCustomer {
        return repository.findById(idOrder).get()
    }

    fun getOrderById(idOrder: Long): OrderCustomer {
        return repository.findById(idOrder).get()
    }

    @Autowired
    private lateinit var repository: OrderRepository
    @Autowired
    private lateinit var basketService: BasketService


}