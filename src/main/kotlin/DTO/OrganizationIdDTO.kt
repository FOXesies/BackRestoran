package org.example.DTO

import org.example.entity.Product
import java.io.InputStream

data class OrganizationIdDTO(
    var idOrganization: Long? = null,
    var name: String,
    var address: String,
    var phoneForUser: String,
    var city: String,
    var descriptions: String?,
    var category: List<String>,
    var products: Map<String, List<Product>>,
    var rating: Double?,
    var ratingCount: Int?,
    var images: List<InputStream?>?
)