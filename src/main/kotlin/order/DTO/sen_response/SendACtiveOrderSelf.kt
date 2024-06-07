package org.example.order.DTO.sen_response

import jakarta.persistence.*
import lombok.ToString
import org.example.entity.Users_.Users
import org.example.order.model.StatusOrder
import org.example.order.model.active.ProductInOrder
import org.example.organization.model.Organization
import org.example.organization_city.model.LocationOrganization
import org.example.uuid.model.UUIDCustom
import java.time.LocalDateTime

data class SendACtiveOrderSelf(
    var idOrderSelf: Long? = null,
    var organizationId: Long? = null,
    var organizationName: String? = null,
    var idLocation: LocationOrganization,
    var uuid: UUIDCustom? = null,

    var phoneUser: String? = null,
    var fromTimeCooking: String?,
    var toTimeCooking: String?,

    var status: StatusOrder? = null,

    var summ: Double? = null,
    var comment: String = ""
)