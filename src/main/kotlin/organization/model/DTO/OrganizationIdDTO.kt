package org.example.organization.model.DTO

import org.example.entity.Image
import org.example.products.DTO.ResponseProduct
import org.example.products.entity.Product

data class OrganizationIdDTO(
    var idOrganization: Long? = null,
    var name: String,
    var phoneForUser: String,
    var idImages: List<Image>,
    var descriptions: String?,
    var category: List<String>,
    var locationsAll: Map<String, List<CityOrganization>> = mutableMapOf(),
    var products: Map<String, MutableList<ResponseProduct>>,
    var rating: Double?,
    var ratingCount: Int?,
    var isFavorite: Boolean = false
)