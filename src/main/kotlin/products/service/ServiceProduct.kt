package org.example.products.service

import jakarta.transaction.Transactional
import org.example.organization.model.DTO.OrganizationIdDTO
import org.example.organization.service.ServiceOrganization
import org.example.organization_city.model.LocationOrganization
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
    private lateinit var imageService: ImageSearchUtils

    fun getProduct(id: Long): Product {
        return productRepository.findById(id).get()
    }

    fun getProductBasic(id: Long): org.example.products.DTO.ResponseProduct {
        return MapperUtils.mapResponseProductInResponseProduct(productRepository.findById(id).get())
    }

    @Transactional
    fun insert(product: Product): Product {
        return productRepository.save(product)
    }


}