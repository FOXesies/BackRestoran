package org.example.products.service

import org.example.organization.service.ServiceOrganization
import org.example.products.entity.Product
import org.example.products.entity.ResponseProduct
import org.example.products.repository.ProductRepository
import org.example.service.ImageSearchUtils
import org.example.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ServiceProduct {

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var organizationService: ServiceOrganization

    @Autowired
    private lateinit var imageService: ImageSearchUtils

    fun getProduct(id: Long): Product {
        return productRepository.findById(id).get()
    }


    //FUNC ADMIN
    fun getBasicinfo(idProduct: Long): Product {
        return productRepository.findById(idProduct).get()
    }
    fun updateBasicinfo(productUpdate: ResponseProduct, file: MultipartFile) {/*
        val product = productRepository.findById(productUpdate.idProduct!!).get()
        product.name = productUpdate.name
        productRepository.save(product)*/
    }
    fun addProduct(productUpdate: ResponseProduct, file: MultipartFile) {
        val product = MapperUtils.mapResponseProductInProduct(productUpdate)
        val productEnd = productRepository.save(product)
        imageService.insertImageProduct(productEnd.idProduct!!, file)

        organizationService.insertProduct(productUpdate.idOrg, product, productUpdate.category)
    }

}