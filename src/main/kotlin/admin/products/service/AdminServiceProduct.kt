package org.example.admin.products.service

import org.example.admin.products.controller.ProductDToUpdate
import org.example.entity.Image
import org.example.organization.service.ServiceOrganization
import org.example.products.entity.Product
import org.example.products.entity.ResponseProduct
import org.example.products.repository.ProductRepository
import org.example.products_category.service.ServiceCategory
import org.example.service.ImageSearchUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class AdminServiceProduct {

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var organizationService: ServiceOrganization

    @Autowired
    private lateinit var imageService: ImageSearchUtils

    @Autowired
    private lateinit var categoryService: ServiceCategory

    fun getBasicinfo(idProduct: Long): Product {
        return productRepository.findById(idProduct).get()
    }

    fun updateProduct(file: List<MultipartFile>, product: ProductDToUpdate) {
        val productEntity = productRepository.findById(product.id).get()
        productEntity.name = product.name
        productEntity.weight = product.weight
        productEntity.description = product.description
        productEntity.price = product.price
        productEntity.category = categoryService.uniqueOrNew(product.category)
        productEntity.images.clear()
        productEntity.images.addAll(product.image?.mapIndexed { index, image -> Image(value = file[index].bytes, main = image.main) }?.toMutableList()?: mutableListOf())
        if(product.image?.any { it.main } == true){
            productEntity.images[0].main = true
        }
        productRepository.save(productEntity)
    }
}