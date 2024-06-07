package org.example.order.DTO.sen_response

import org.example.order.model.AddressUser
import org.example.order.model.StatusOrder
import org.example.organization_city.model.LocationOrganization

data class SendOrderPreview(
    var idOrder: Long? = null,
    var organizationId: Long? = null,
    var organizationName: String? = null,
    var addressUser: AddressUser? = null,
    var idLocation: LocationOrganization? = null,

    var fromTimeCooking: String?,
    var toTimeCooking: String?,

    var status: StatusOrder? = null,

    var summ: Double? = null,
    var comment: String = ""
)