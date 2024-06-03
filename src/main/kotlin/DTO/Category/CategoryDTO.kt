package org.example.DTO.Category

import org.example.entity.Image


data class CategoryDTO(
    val name: String,
    val image: List<Image>?
)