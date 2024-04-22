package org.example.controller

import org.example.DTO.Basket.BasketItemDtom
import org.example.DTO.Basket.SendBasketProduct
import org.example.entity.Basket.BasketItem
import org.example.service.BasketService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/basket")
class ControllerBasket {

    @Autowired
    lateinit var basketService: BasketService

    @RequestMapping(path = ["/{id}"], method = [RequestMethod.GET],
    produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getBasketByUser(@PathVariable(value = "id") id: Long): BasketItemDtom? {
        return basketService.getBasketByUserIdDto(id)
    }
/*    @RequestMapping(path = ["/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getBasketByUser(@PathVariable(value = "id") id: Long): BasketItem? {
        return basketService.getBasketByUserId(id).get()
    }*/
    @RequestMapping(path = ["/add_product"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addProduct(@RequestBody basket: SendBasketProduct){
        return basketService.addProduct(basket)
    }
    @RequestMapping(path = ["/delete_product"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteProduct(@RequestBody product: SendBasketProduct){
        return basketService.deleteProduct(product)
    }
    @RequestMapping(path = ["/plus_product"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun plusCountProduct(@RequestBody product: SendBasketProduct){
        return basketService.plusCount(product)
    }
    @RequestMapping(path = ["/minus_product"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun minusCountProduct(@RequestBody product: SendBasketProduct){
        return basketService.minusCount(product)
    }

}