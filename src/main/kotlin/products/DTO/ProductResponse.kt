package org.example.products.DTO

import org.example.entity.Image

data class ResponseProduct (
    var id: Long,
    var name: String,
    var price: Double?,
    var image: Image? = null,
    var description: String?,
)