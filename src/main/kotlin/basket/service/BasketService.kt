package org.example.basket.service

import org.example.DTO.Basket.BasketItemDtom
import org.example.DTO.Basket.SendBasketProduct
import org.example.basket.entity.BasketItem
import org.example.basket.entity.ProductBasket
import org.example.products.DTO.ResponeInt
import org.example.basket.repository.BasketRepository
import org.example.organization.model.Organization
import org.example.organization.repository.OrganizationRepository
import org.example.organization.service.ServiceOrganization
import org.example.products.repository.ProductRepository
import org.example.repository.BasicUserRepository
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

    @Autowired
    lateinit var userRepository: BasicUserRepository

    @Autowired
    lateinit var organizationService: OrganizationRepository

    fun getBasketByUserId(userId: Long): Optional<BasketItem> {
        return basketRepository.findById(userId)
    }

    fun getBasketByUserIdDto(userId: Long): BasketItemDtom {
        return MapperUtils.mapBasketInDTO(basketRepository.findById(userId).get(), productRepository)
    }

    fun addProduct(product: SendBasketProduct) {
        val curBasket = basketRepository.findById(product.UserId!!)

        if (curBasket.isPresent) {
            val curBasket_ = curBasket.get()

            if (curBasket_.organization == null) {
                val organization = organizationService.findById(product.organziationId!!)
                if (organization.isPresent) {
                    curBasket_.organization = organization.get()
                } else {
                    // Обработка случая, когда организация не найдена
                    throw IllegalArgumentException("Organization not found")
                }
            }
            curBasket_.city = product.city

            val productEntity = productRepository.findById(product.productId!!)
            if (productEntity.isPresent) {
                val productPrice = productEntity.get().price ?: 0.0
                val listProduct = curBasket_.productsPick.toMutableList()
                val newProduct = ProductBasket(
                    product = productEntity.get(),
                    count = 1
                )
                listProduct.add(newProduct)
                curBasket_.productsPick = listProduct
                curBasket_.summ += productPrice
                basketRepository.save(curBasket_)
            } else {
                // Обработка случая, когда продукт не найден
                throw IllegalArgumentException("Product not found")
            }
        } else {
            // Обработка случая, когда корзина не найдена
            throw IllegalArgumentException("Basket not found")
        }
    }

    private fun mathSumm(math: MutableList<ProductBasket>): Double{
        return math.sumOf { it.count * it.product.price!! }
    }

    fun deleteProduct(body: SendBasketProduct) {
        val curBasket = basketRepository.findById(body.UserId!!)
            .orElseThrow { IllegalArgumentException("Basket not found") }

        val productToRemove = curBasket.productsPick.find { it.product.idProduct == body.productId }
            ?: throw IllegalArgumentException("Product not found in basket")

        val listProduct = curBasket.productsPick.toMutableList()
        listProduct.remove(productToRemove)

        curBasket.productsPick = listProduct
        curBasket.summ = listProduct.sumOf { it.product.price ?: 0.0 }

        // Если корзина пустая после удаления последнего продукта
        if (curBasket.productsPick.isEmpty()) {
            curBasket.organization = null
        }

        basketRepository.save(curBasket)
    }

    fun minusCount(body: SendBasketProduct){
        var basket = getBasketByUserId(body.UserId!!).get()
        val product = productRepository.findById(body.productId!!)
        basket.summ -= product.get().price!!
        basket.productsPick.find { it.product.idProduct == body.productId }!!.count--
        basketRepository.save(basket)
    }

    fun plusCount(body: SendBasketProduct){
        var basket = getBasketByUserId(body.UserId!!).get()

        val product = productRepository.findById(body.productId!!)
        basket.summ += product.get().price!!
        basket.productsPick.find { it.product.idProduct == body.productId }!!.count++
        basketRepository.save(basket)
    }

    fun checkProductInBasket(idUser: Long, idProduct: Long): ResponeInt {
        val basket = getBasketByUserId(idUser).get()
        val productCount = basket.productsPick.find { it.product.idProduct == idProduct }?.count
        return ResponeInt(productCount?: 0)
    }

    fun replaceAll(product: SendBasketProduct) {
        val curBasket = basketRepository.findById(product.UserId!!)
        val curBasket_ = curBasket.get()
        curBasket_.organization = organizationService.findById(product.organziationId!!).get()
        curBasket_.city = product.city

        val listProduct = curBasket_.productsPick.toMutableList()
        val newProduct = ProductBasket(product = productRepository.findById(product.productId!!).get(), count = 1)
        listProduct.clear()
        listProduct.add(newProduct)
        curBasket_.productsPick = listProduct

        curBasket_.summ = productRepository.findById(product.productId!!).get().price!!
        basketRepository.save(curBasket_)
    }

}