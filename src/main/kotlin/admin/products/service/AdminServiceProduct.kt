package org.example.admin.products.service

import org.example.organization.repository.OrganizationRepository
import org.example.organization.service.ServiceOrganization
import org.example.products.entity.Product
import org.example.products.entity.ResponseProduct
import org.example.products.repository.ProductRepository
import org.example.products_category.service.ServiceCategory
import org.example.service.ImageSearchUtils
import org.example.utils.MapperUtils
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

    fun addProduct(productUpdate: ResponseProduct, file: MultipartFile) {
        if(productUpdate.product.idProduct == null) {
            val product = MapperUtils.mapResponseProductInProduct(productUpdate, categoryService.uniqueOrNew(productUpdate.category))

            val productEnd = productRepository.save(product)
            imageService.insertImageProduct(productEnd.idProduct!!, file)

            organizationService.insertProduct(productUpdate.idOrg, product, productUpdate.category)
        }
        else {
            productRepository.save(productUpdate.product)
            imageService.insertImageProduct(productUpdate.product.idProduct!!, file)
        }
    }
}