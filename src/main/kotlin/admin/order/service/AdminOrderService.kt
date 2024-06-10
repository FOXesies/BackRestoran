package org.example.admin.order.service

import org.example.admin.order.model.AdminStatusResponse
import org.example.order.DTO.sen_response.SendCanceledOrderPreview
import org.example.order.DTO.sen_response.SendCompleteOrderDTO
import org.example.order.DTO.sen_response.SendOrderPreview
import org.example.order.model.StatusOrder
import org.example.order.model.active.CanceledInfo
import org.example.order.model.active.OrderCustomer
import org.example.order.repository.active.OrderRepository
import org.example.order.util.ResponseCancel
import org.example.organization.repository.OrganizationRepository
import org.example.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

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
        return orderRepository.findAllByOrganization(organization)
            .filter { it.status != StatusOrder.COMPLETE && it.status != StatusOrder.CANCELE }
            .map { MapperUtils.mapOrderToPreview(it) }
    }
    fun getOrdersCancel(idOrg: Long): List<SendCanceledOrderPreview> {
        val organization = organizationRepository.findById(idOrg).get()
        return orderRepository.findAllByOrganization(organization).filter { it.status == StatusOrder.CANCELE }.map { MapperUtils.mapOrderToCancelPreview(it) }
    }
    fun getOrdersComplete(idOrg: Long): List<SendCompleteOrderDTO> {
        val organization = organizationRepository.findById(idOrg).get()
        return orderRepository.findAllByOrganization(organization).filter { it.status == StatusOrder.COMPLETE }.map { MapperUtils.mapCompleteOrderToPreview(it) }
    }

    fun cancelOrder(orderInfo: ResponseCancel) {
        val order = orderRepository.findById(orderInfo.idOrder!!).get()
        order.status = StatusOrder.CANCELE
        order.canceledInfo = CanceledInfo(commnet = orderInfo.comment, timeCancel = LocalDateTime.now())
        orderRepository.save(order)
    }

}