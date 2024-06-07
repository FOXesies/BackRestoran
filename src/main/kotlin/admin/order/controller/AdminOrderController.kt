package org.example.admin.order.controller

import org.example.admin.order.model.AdminStatusResponse
import org.example.admin.order.service.AdminOrderService
import org.example.order.model.active.OrderCustomer
import org.example.order.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/admin/order")
class AdminOrderController {

    @Autowired
    private lateinit var orderService: AdminOrderService


/*    @RequestMapping(path = ["/all_deliveri/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOrder(@PathVariable(value = "id") userId: Long): List<OrderCustomer>{
        return orderService.getOrders(userId)
    }*/


/*
    @RequestMapping(path = ["/all_self_deliveri/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOrderSelf(@PathVariable(value = "id") orgId: Long): List<OrderSelfDelivery>{
        return orderService.getOrdersSelf(orgId)
    }
*/

/*    @RequestMapping(path = ["/switch_status"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun switchStatus(@RequestBody statusResponse: AdminStatusResponse){
        return orderService.switchStatus(statusResponse)
    }

    @RequestMapping(path = ["/switch_statusSelf"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun switchStatusSelf(@RequestBody statusResponse: AdminStatusResponse){
        return orderService.switchStatusSelf(statusResponse)
    }*/

}