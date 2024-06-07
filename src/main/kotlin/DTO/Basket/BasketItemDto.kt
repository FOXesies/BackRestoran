package org.example.DTO.Basket

import org.example.products.entity.Product

data class BasketItemDtom(
    var idRestoraunt: Long? = null,
    var productsPick: MutableList<ProductInBasket>? = mutableListOf(),
    var summ: Double? = 0.0,
    var city: String? = null
)

data class ProductInBasket(
    var product: Long? = null,
    var count: Int? = 0
)