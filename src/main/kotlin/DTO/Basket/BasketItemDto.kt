package org.example.DTO.Basket

import org.example.entity.Product.Product

data class BasketItemDtom(
    var idRestoraunt: Long? = null,
    var productsPick: MutableList<ProductInBasket>? = mutableListOf(),
    var summ: Double? = 0.0
)

data class ProductInBasket(
    var product: Product? = null,
    var count: Int? = 0
)