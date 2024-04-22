package org.example.order.controller

import org.example.order.model.OrderCustomer
import org.example.order.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/order")
class OrderController {

    @Autowired
    private lateinit var orderService: OrderService

    @RequestMapping(path = ["/deliveri/add"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addOrder(@RequestBody order: OrderCustomer){
        return orderService.sendOrder(order)
    }

    @RequestMapping(path = ["/deliveri/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOrderById(@PathVariable(value = "id") idOrder: Long): OrderCustomer{
        return orderService.getOrderById(idOrder)
    }
}