package org.example.order.DTO.sen_response

import org.example.order.model.AddressUser
import org.example.order.model.StatusOrder
import org.example.organization_city.model.LocationOrganization

data class SendCompleteOrderDTO(
    val rating: Int? = null,
    val orderPreview: SendOrderPreview? = null
)