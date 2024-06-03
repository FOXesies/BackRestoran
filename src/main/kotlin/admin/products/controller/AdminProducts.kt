package org.example.admin.products.controller

import org.example.admin.products.service.AdminServiceProduct
import org.example.organization.service.ServiceOrganization
import org.example.products.entity.ResponseProduct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@RestController
@RequestMapping("api/v1/admin/products")
class AdminProducts {

    @Autowired
    private lateinit var serviseProduct: AdminServiceProduct

    @PostMapping("/add_product/")
    fun updateProduct(
        @RequestPart("image") file: MultipartFile,
        @RequestPart("product") product: ResponseProduct
    ): ResponseEntity<Void> {
        try {
            serviseProduct.addProduct(product, file)
            return ResponseEntity.ok().build()
        } catch (e: IOException) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}