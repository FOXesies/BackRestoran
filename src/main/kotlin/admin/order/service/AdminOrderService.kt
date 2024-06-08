package org.example.admin.order.service

import org.example.admin.order.model.AdminStatusResponse
import org.example.order.DTO.sen_response.SendOrderPreview
import org.example.order.model.active.OrderCustomer
import org.example.order.repository.active.OrderRepository
import org.example.organization.repository.OrganizationRepository
import org.example.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AdminOrderService {

    @Autowired
    private lateinit var organizationRepository: OrganizationRepository

    @Autowired
    private lateinit var orderRepository: OrderRepository

    fun switchStatus(statusRespone: AdminStatusResponse){
        val order = orderRepository.findById(statusRespone.id).get()
        order.status = statusRespone.status
        orderRepository.save(order)
    }
    fun getOrders(idOrg: Long): List<SendOrderPreview> {
        val organization = organizationRepository.findById(idOrg).get()
        return orderRepository.findAllByOrganization(organization).map { MapperUtils.mapOrderToPreview(it) }
    }

    /*fun switchStatusSelf(statusRespone: AdminStatusResponse){
        val order = orderSelfRepository.findById(statusRespone.id).get()
        order.status = statusRespone.status
        orderSelfRepository.save(order)
    }

    fun getOrders(idOrg: Long): List<OrderCustomer>{
        val organization = organizationRepository.findById(idOrg).get()
        return orderRepository.findAllByOrganization(organization)
    }
    fun getOrdersSelf(idOrg: Long): List<OrderSelfDelivery>{
        val organization = organizationRepository.findById(idOrg).get()
        return orderSelfRepository.findAllByOrganization(organization)
    }*/

}