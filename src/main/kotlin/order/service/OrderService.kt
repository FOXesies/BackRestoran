package org.example.order.service

import order.repository.active.CanceledInfoRepository
import org.example.order.model.active.OrderCustomer
import org.example.order.repository.active.OrderRepository
import org.example.basket.service.BasketService
import org.example.feedbacks.entity.FeedBacks
import org.example.order.DTO.ActiveOrderDTO
import org.example.order.DTO.sen_response.*
import org.example.order.model.StatusOrder
import org.example.order.model.active.CanceledInfo
import org.example.order.util.ResponseCancel
import org.example.organization_city.repository.LocationOrganizationRepository
import org.example.products.DTO.ResponseProduct
import org.example.repository.BasicUserRepository
import org.example.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class OrderService {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

    fun findOrderById(idOrder: Long): OrderCustomer {
        return repository.findById(idOrder).get()
    }

    fun sendOrder(order: ActiveOrderDTO) {
        val order =
            OrderCustomer(
                user = userRepository.findById(order.idUser).get(),
                addressUser = order.addressUser,
                phoneUser = order.phoneUser,
                paymentType = order.payment,
                idLocation = if(order.idLocation != null) repositoryLocationOrganizationRepository.findById(order.idLocation!!).get() else null,
                fromTimeDelivery = LocalDateTime.now(),
                toTimeDelivery = LocalDateTime.parse(order.toTimeDelivery, formatter),
                status = StatusOrder.WAIT_ACCEPT,
                isSelf = order.isSelf,
                summ = order.summ,
                comment = order.comment
        )

        val basket = basketService.getBasketByUserId(order.user!!.profileUUID!!).get()
        val products = MapperUtils.mapProductBasketInOrder(basket.productsPick)
        order.productOrder = products
        order.organization = basket.organization
        repository.save(order)

        basket.organization = null
        basket.city = null
        basket.productsPick.clear()
        basketService.basketRepository.save(basket)
    }

    fun getBasicProduct(idOrder: Long): SendBasicInfoOrder {
        val order = repository.findById(idOrder).get()
        return MapperUtils.mapOrderInSendFull(order)
    }
    fun getBasicProductUser(idOrder: Long): SendBasicInfoOrderUser {
        val order = repository.findById(idOrder).get()
        return MapperUtils.mapOrderInSendFullUser(order)
    }

    fun getBasicProductUser(responseFeedbackCreateDTO: FeedbackCreateDTO) {
        val order = repository.findById(responseFeedbackCreateDTO.idOrder).get()
        order.feedBacks = FeedBacks(null, order.organization!!, order.user!!,
            rating = responseFeedbackCreateDTO.rating, comentRating = responseFeedbackCreateDTO.comment, timeComment = LocalDateTime.now())
        repository.save(order)
    }

    fun getOrderById(idOrder: Long): OrderCustomer {
        return repository.findById(idOrder).get()
    }
    fun getActiveOrders(userId: Long): List<SendOrderPreview> {
        val user = userRepository.findById(userId).get()
        return repository.findAllByUser(user)
            .filter { it.status != StatusOrder.COMPLETE && it.status != StatusOrder.CANCELE }
            .map { MapperUtils.mapOrderToPreview(it) }.sortedBy { it.idOrder }.reversed()
    }
    fun getCancelOrders(userId: Long): List<SendCanceledOrderPreview> {
        val user = userRepository.findById(userId).get()
        return repository.findAllByUser(user)
            .filter { it.status == StatusOrder.CANCELE }
            .map { MapperUtils.mapOrderToCancelPreview(it) }.sortedBy { it.orderPreview!!.idOrder }.reversed()
    }
    fun getCompleteOrders(userId: Long): List<SendCompleteOrderDTO> {
        val user = userRepository.findById(userId).get()
        return repository.findAllByUser(user)
            .filter { it.status == StatusOrder.COMPLETE }
            .map { MapperUtils.mapCompleteOrderToPreview(it) }.sortedBy { it.orderPreview!!.idOrder }.reversed()
    }
    /*fun sendOrderSelf(order: ActiveOrderSelfDTO) {
        val order =
            OrderSelfDelivery(
                user = userRepository.findById(order.idUser).get(),
                idLocation = repositoryLocationOrganizationRepository.findById(order.idLocation).get(),
                phoneUser = order.phoneUser,
                fromTimeCooking = LocalDateTime.parse(order.fromTimeCooking!!, formatter),
                toTimeCooking = LocalDateTime.parse(order.fromTimeCooking!!, formatter),
                status = StatusOrder.WAIT_ACCEPT,
                summ = order.summ,
                comment = order.comment
            )
        val basket = basketService.getBasketByUserId(order.user.profileUUID!!).get()
        val products = MapperUtils.mapProductBasketInOrder(basket.productsPick)
        order.productOrder = products
        order.organization = basket.organization!!
        order.uuid = UUIDCustom()
        repositorySelf.save(order)
    }*/

    /*fun getOrdeSelfrById(idOrder: Long): OrderSelfDelivery {
        return repositorySelf.findById(idOrder).get()
    }*/
    fun cancelOrder(orderInfo: ResponseCancel) {
        val order = repository.findById(orderInfo.idOrder!!).get()
        order.status = StatusOrder.CANCELE
        order.canceledInfo = CanceledInfo(commnet = orderInfo.comment, timeCancel = LocalDateTime.now())
        repository.save(order)
    }

    @Autowired
    private lateinit var userRepository: BasicUserRepository
    @Autowired
    private lateinit var repository: OrderRepository
    @Autowired
    private lateinit var basketService: BasketService
    @Autowired
    private lateinit var repositoryLocationOrganizationRepository: LocationOrganizationRepository

}