package org.example.favorite.controller

import org.example.favorite.entity.DTO.ResponseFavProduct
import org.example.favorite.service.FavoriteProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/favorite")
class ControllerFavorite {

    @Autowired
    private lateinit var favoriteProduct: FavoriteProductService

    @RequestMapping(path = ["/like_product/"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun completeOrder(@PathVariable(value = "id") orderId: Long) {
        return favoriteProduct.dislikeProduct(ResponseFavProduct(1, 1))
    }
}