package org.example.products.entity

data class ResponseProduct (
    var product: Product,
    var image: List<ByteArray>? = null,
    var idOrg: Long,
    var category: String
)