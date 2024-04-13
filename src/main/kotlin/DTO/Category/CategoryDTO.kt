package org.example.DTO.Category

import org.example.entity.OrganizationImagesProfile

data class CategoryDTO(
    val name: String,
    val image: List<OrganizationImagesProfile>?
)