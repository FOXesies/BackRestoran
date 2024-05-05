package org.example.order.controller

import org.example.order.model.completed.CompleteOrder
import org.example.order.model.completed.CompleteOrderSelf
import org.example.order.service.CompleteOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/complete_order")
class CompleteOrderController {

    @Autowired
    private lateinit var orderService: CompleteOrderService

    @RequestMapping(path = ["/complete/{id}"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun completeOrder(@PathVariable(value = "id") orderId: Long) {
        return orderService.sendOrder(orderId)
    }
    @RequestMapping(path = ["/complete/self/{id}"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun completeOrderSelf(@PathVariable(value = "id") orderId: Long) {
        return orderService.sendOrderSelf(orderId)
    }

    @RequestMapping(path = ["/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOrderSelf(@PathVariable(value = "id") userId: Long): List<CompleteOrder> {
        return orderService.getOrdersByUser(userId)
    }

    @RequestMapping(path = ["/self/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOrders(@PathVariable(value = "id") userId: Long): List<CompleteOrderSelf> {
        return orderService.getOrdersSelfByUser(userId)
    }

}