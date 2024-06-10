package org.example.order.DTO.sen_response

import jakarta.persistence.*
import lombok.ToString
import org.example.entity.Users_.Users
import org.example.feedbacks.entity.FeedBacks
import org.example.order.model.AddressUser
import org.example.order.model.StatusOrder
import org.example.order.model.StatusPayment
import org.example.order.model.active.CanceledInfo
import org.example.order.model.active.ProductInOrder
import org.example.organization.model.Organization
import org.example.organization_city.model.LocationOrganization
import org.example.products.DTO.ResponseProduct
import java.time.LocalDateTime

data class SendBasicInfoOrder(
    val orderId: Long = 0,

    var userName: String? = null,

    var addressUser: AddressUser? = null,
    var phoneUser: String? = null,
    var fromTimeDelivery: String? = null,
    var toTimeDelivery: String? = null,
    var productOrder: List<ResponseProduct> = mutableListOf(),
    var status: StatusOrder? = null,
    var isSelf: Boolean? = null,
    var idLocation: LocationOrganization? = null,
    var payment: StatusPayment? = null,
    var counts: List<Int> = mutableListOf(),

    var canceledInfo: String? = null,
    var canceledTime: String? = null,

    var feedBacksRating: Int? = null,
    var feedBacksComment: String? = null,
    var timeComment: String? = null,

    var summ: Double? = null,
    var comment: String? = null
)
data class SendBasicInfoOrderUser(
    val orderId: Long = 0,

    var orgName: String? = null,
    var orgPhone: String? = null,

    var addressUser: AddressUser? = null,
    var phoneUser: String? = null,
    var fromTimeDelivery: String? = null,
    var toTimeDelivery: String? = null,
    var productOrder: List<ResponseProduct> = mutableListOf(),
    var counts: List<Int> = mutableListOf(),
    var status: StatusOrder? = null,
    var isSelf: Boolean? = null,
    var idLocation: LocationOrganization? = null,

    var canceledInfo: String? = null,
    var canceledTime: String? = null,

    var feedBacksRating: Int? = null,
    var feedBacksComment: String? = null,
    var timeComment: String? = null,
    var payment: StatusPayment? = null,

    var summ: Double? = null,
    var comment: String? = null
)