package org.example.admin.order.controller

import org.example.admin.order.model.AdminStatusResponse
import org.example.admin.order.service.AdminOrderService
import org.example.order.DTO.sen_response.SendCanceledOrderPreview
import org.example.order.DTO.sen_response.SendCompleteOrderDTO
import org.example.order.DTO.sen_response.SendOrderPreview
import org.example.order.model.active.OrderCustomer
import org.example.order.service.OrderService
import org.example.order.util.ResponseCancel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

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

    @RequestMapping(path = ["/active_order/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getActiveOrders(@PathVariable(value = "id") idOrder: Long): List<SendOrderPreview> {
        return orderService.getOrders(idOrder)
    }

    @RequestMapping(path = ["/complete_order/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getActiveComplete(@PathVariable(value = "id") idOrder: Long): List<SendCompleteOrderDTO> {
        return orderService.getOrdersComplete(idOrder)
    }

    @RequestMapping(path = ["/cancele_order/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCancelerders(@PathVariable(value = "id") idOrder: Long): List<SendCanceledOrderPreview> {
        return orderService.getOrdersCancel(idOrder)
    }

    @RequestMapping(path = ["/cancel/"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun cancelOrder(@RequestBody responseCancel: ResponseCancel) {
        return orderService.cancelOrder(responseCancel)
    }

}