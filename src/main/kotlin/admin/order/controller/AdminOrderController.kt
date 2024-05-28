package org.example.admin.order.controller

import org.example.admin.order.model.AdminStatusResponse
import org.example.admin.order.service.AdminOrderService
import org.example.order.model.active.OrderCustomer
import org.example.order.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/admin/order")
class AdminOrderController {

    @Autowired
    private lateinit var orderService: AdminOrderService

    @RequestMapping(path = ["/switch_status"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun switchStatus(@RequestBody statusResponse: AdminStatusResponse){
        return orderService.switchStatus(statusResponse)
    }

    @RequestMapping(path = ["/switch_statusSelf"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun switchStatusSelf(@RequestBody statusResponse: AdminStatusResponse){
        return orderService.switchStatusSelf(statusResponse)
    }

}