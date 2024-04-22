package org.example.DTO.Organization

import org.example.DTO.Category.CategoryDTO

data class OrganizationDTO(
    var idOrganization: Long? = null,
    var name: String,
    var phoneForUser: String,
    var cities: Map<String?, List<String?>>,
    var idImage: Long,
    var descriptions: String?,
    var category: List<CategoryDTO>,
    var rating: Double?,
    var ratingCount: Int?,
    var images: List<ByteArray?>?
)