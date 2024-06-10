package org.example.admin.products.model

import org.example.organization_city.model.DTO.CityOrganization
import organization.model.DTO.ImageDTO

data class BasicInfoResponseADD(
    val idOrg: Long,
    val name: String,
    val phone: String,
    val description: String? = null,
    val locationAll: Map<String, MutableList<CityOrganization>> = mutableMapOf(),
    var idImages: MutableList<ImageDTO>? = null,
    var idUser: Long? = null
)