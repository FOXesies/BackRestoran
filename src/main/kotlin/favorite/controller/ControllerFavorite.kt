package org.example.favorite.controller

import org.example.entity.Product.Product
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/favorite")
class ControllerFavorite {

    fun getFavoriteByUser(@RequestParam("id_user") idUser: Long): List<Product>{

    }

}