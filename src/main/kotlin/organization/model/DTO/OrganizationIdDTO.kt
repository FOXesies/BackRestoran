package org.example.organization.model.DTO

import org.example.entity.Image
import org.example.organization_city.model.DTO.CityOrganization
import org.example.products.DTO.ResponseProduct

data class OrganizationIdDTO(
    var idOrganization: Long? = null,
    var name: String,
    var phoneForUser: String,
    var idImages: List<Image>,
    var descriptions: String?,
    var category: Set<String>,
    var locationsAll: Map<String, List<CityOrganization>> = mutableMapOf(),
    var products: Map<String, MutableList<ResponseProduct>>,
    var rating: Double?,
    var ratingCount: Int?
)