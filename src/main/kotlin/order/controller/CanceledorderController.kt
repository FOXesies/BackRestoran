package org.example.order.controller

import org.example.order.model.active.OrderCustomer
import org.example.order.model.canceled.CanceledOrder
import org.example.order.model.canceled.CanceledOrderSelf
import org.example.order.service.CanceledOrderService
import org.example.order.util.ResponseCancel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/canceled_order")
class CanceledorderController {

    @Autowired
    private lateinit var canceledOrderService: CanceledOrderService

    @RequestMapping(path = ["/all_deliveri/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOrder(@PathVariable(value = "id") userId: Long): List<CanceledOrder>{
        return canceledOrderService.getCanceledOrders(userId)
    }
    @RequestMapping(path = ["/all_self_deliveri/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOrderSelf(@PathVariable(value = "id") userId: Long): List<CanceledOrderSelf>{
        return canceledOrderService.getCanceledSelfOrders(userId)
    }
    @RequestMapping(path = ["/delivery/cancel/"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun cancelDeliveryOrder(@RequestBody order: ResponseCancel){
        canceledOrderService.cancelOrder(order)
    }
    @RequestMapping(path = ["/self_delivery/cancel/"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun cancelDeliveryOrderSelf(@RequestBody order: ResponseCancel){
        canceledOrderService.cancelOrderSelf(order)
    }

}