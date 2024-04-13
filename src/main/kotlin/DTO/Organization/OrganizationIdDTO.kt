package org.example.DTO.Organization

import org.example.entity.Product.Product

data class OrganizationIdDTO(
    var idOrganization: Long? = null,
    var name: String,
    var address: String,
    var phoneForUser: String,
    var city: String,
    var idImage: Long,
    var descriptions: String?,
    var category: List<String>,
    var products: Map<String, List<Product>>,
    var rating: Double?,
    var ratingCount: Int?,
    var isFavorite: Boolean = false,
    var images: List<ByteArray?>?
)