package org.example.organization.model.DTO

import org.example.DTO.Category.CategoryDTO
import org.example.entity.Image

data class OrganizationDTO(
    var idOrganization: Long? = null,
    var name: String,
    var phoneForUser: String,
    var cities: Map<String?, List<String?>>,
    var idImages: List<Image>,
    var descriptions: String?,
    var category: List<CategoryDTO>,
    var rating: Double?,
    var ratingCount: Int?,
)