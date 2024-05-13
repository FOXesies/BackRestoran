package org.example.service

import org.example.DTO.Basket.BasketItemDtom
import org.example.DTO.Basket.SendBasketProduct
import org.example.entity.Basket.BasketItem
import org.example.entity.Basket.ProductBasket
import org.example.products.DTO.ResponeInt
import org.example.repository.BasketRepository
import org.example.products.repository.ProductRepository
import org.example.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class BasketService {

    @Autowired
    lateinit var basketRepository: BasketRepository

    @Autowired
    lateinit var productRepository: ProductRepository

    fun getBasketByUserId(userId: Long): Optional<BasketItem> {
        return basketRepository.findById(userId)
    }

    fun getBasketByUserIdDto(userId: Long): BasketItemDtom {
        return MapperUtils.mapBasketInDTO(basketRepository.findById(userId).get(), productRepository)
    }

    fun addProduct(product: SendBasketProduct) {
        var curBasket = basketRepository.findById(product.UserId!!)
        val curBasket_ = curBasket.get()

        var listProduct = curBasket_.productsPick.toMutableList()
        val newProduct = ProductBasket(productId = product.productId!!, count = 1)
        listProduct.add(newProduct)
        curBasket_.productsPick = listProduct

        curBasket_.summ += productRepository.findById(product.productId!!).get().price!!
        basketRepository.save(curBasket_)
    }

    private fun mathSumm(math: MutableList<ProductBasket>): Double{
        return math.sumOf { it.count * productRepository.findById(it.productId).get().price!! }
    }

    fun deleteProduct(body: SendBasketProduct){
        var curBasket = basketRepository.findById(body.UserId!!).get()
        if(curBasket.productsPick.size == 1){
            val curBasket_ = BasketItem(idUser = body.UserId, productsPick = mutableListOf(), summ = 0.0)
            basketRepository.save(curBasket_)
            return
        }
        var item = curBasket.productsPick.find { it.productId == body.productId }

        var listProduct = curBasket.productsPick.toMutableList()
        listProduct.remove(item)
        curBasket.productsPick = listProduct

        curBasket.summ = mathSumm(listProduct)
        basketRepository.save(curBasket)
    }
    fun minusCount(body: SendBasketProduct){
        var basket = getBasketByUserId(body.UserId!!).get()
        val product = productRepository.findById(body.productId!!)
        basket.summ -= product.get().price!!
        basket.productsPick.find { it.productId == body.productId }!!.count--
        basketRepository.save(basket)
    }

    fun plusCount(body: SendBasketProduct){
        var basket = getBasketByUserId(body.UserId!!).get()
        val product = productRepository.findById(body.productId!!)
        basket.summ += product.get().price!!
        basket.productsPick.find { it.productId == body.productId }!!.count++
        basketRepository.save(basket)
    }

    fun checkProductInBasket(idUser: Long, idProduct: Long): ResponeInt {
        val basket = getBasketByUserId(idUser).get()
        val productCount = basket.productsPick.find { it.productId == idProduct }?.count
        return ResponeInt(productCount?: 0)
    }

}