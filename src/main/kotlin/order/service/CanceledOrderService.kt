package org.example.order.service

import org.example.order.model.canceled.CanceledOrder
import org.example.order.model.canceled.CanceledOrderSelf
import org.example.order.repository.canceled.CanceledOrderRepository
import org.example.order.repository.active.OrderRepository
import org.example.order.repository.active.OrderSelfRepository
import org.example.order.repository.canceled.CanceledOrderSelfRepository
import org.example.order.util.ResponseCancel
import org.example.repository.BasicUserRepository
import org.example.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CanceledOrderService {

    @Autowired
    private lateinit var userRepository: BasicUserRepository
    @Autowired
    private lateinit var repository: OrderRepository
    @Autowired
    private lateinit var repositorySelf: OrderSelfRepository
    @Autowired
    private lateinit var canceledOrderRepository: CanceledOrderRepository
    @Autowired
    private lateinit var canceledOrderSelfRepository: CanceledOrderSelfRepository

    fun getCanceledOrders(idUser: Long): List<CanceledOrder> {
        val user = userRepository.findById(idUser).get()
        return canceledOrderRepository.findAllByUser(user)
    }
    fun getCanceledSelfOrders(idUser: Long): List<CanceledOrderSelf> {
        val user = userRepository.findById(idUser).get()
        return canceledOrderSelfRepository.findAllByUser(user)
    }
    fun cancelOrder(orderInfo: ResponseCancel) {
        val order = repository.findById(orderInfo.idOrder!!).get()
        canceledOrderRepository.save(MapperUtils.mapOrderInCanceled(order, order.comment))
        repository.deleteById(order.orderId)
    }
    fun cancelOrderSelf(orderInfo: ResponseCancel) {
        val order = repositorySelf.findById(orderInfo.idOrder!!).get()
        canceledOrderSelfRepository.save(MapperUtils.mapOrderSelfInCanceled(order, order.comment))
        repositorySelf.deleteById(order.idOrderSelf!!)
    }

}