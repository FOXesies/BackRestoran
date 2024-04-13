package org.example.service

import org.example.entity.Product.Product
import org.example.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ServiceProduct {

    @Autowired
    lateinit var productRepository: ProductRepository

    fun getProduct(id: Long): Product {
        return productRepository.findById(id).get()
    }

}