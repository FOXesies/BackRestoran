package org.example.service

import org.example.entity.Orders.Order
import org.example.repository.BasketRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ServiceBasket {

    @Autowired
    lateinit var basketRepository: BasketRepository

    fun getBasketByUser(id: Long): Order{
        return basketRepository.findById(id).get()
    }

}