package org.example.products.entity

data class ResponseProduct (
    var product: Product,
    var image: ByteArray,
    var idOrg: Long,
    var category: String
)