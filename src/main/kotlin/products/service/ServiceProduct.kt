package org.example.products.service

import org.example.products.DTO.ProductResponse
import org.example.products.entity.Product
import org.example.products.repository.ProductRepository
import org.example.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ServiceProduct {

    @Autowired
    lateinit var productRepository: ProductRepository

    fun getProduct(id: Long): Product {
        return productRepository.findById(id).get()
    }


    //FUNC ADMIN
    fun getBasicinfo(idProduct: Long): Product {
        return productRepository.findById(idProduct).get()
    }
    fun updateBasicinfo(productUpdate: Product) {
        val product = productRepository.findById(productUpdate.idProduct!!).get()
        product.name = productUpdate.name
        productRepository.save(product)
    }

}