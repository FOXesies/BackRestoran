package org.example.controller

import org.example.entity.Orders.Order
import org.example.service.ServiceBasket
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController("api/v1/me/basket")
class ControllerBasket {

    @Autowired
    lateinit var basket: ServiceBasket

    @RequestMapping(path = ["/{id}"], method = [RequestMethod.GET],
    produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getBasketByUser(@PathVariable(value = "id") id: Long): Order{
        return basket.getBasketByUser(id)
    }

}