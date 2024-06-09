package org.example.admin.products.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.admin.products.service.AdminServiceProduct
import org.example.entity.Image
import org.example.products.DTO.ResponseProduct
import org.example.products.entity.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.json.GsonJsonParser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@RestController
@RequestMapping("api/v1/admin/products")
class AdminProducts {

    @PostMapping("/upload_foto/")
    fun updateOrgImage(@RequestParam("image") file: List<MultipartFile>,
                       @RequestParam("product") product: String): ResponseEntity<Any> {
        try {
            println(product)
            val objectMapper = ObjectMapper()
            val product: ProductDToUpdate = objectMapper.readValue(product, ProductDToUpdate::class.java)
            println(product.name)
            serviseProduct.updateProduct(file, product)
            return ResponseEntity.ok().build()
        } catch (e: IOException) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @Autowired
    private lateinit var serviseProduct: AdminServiceProduct
}

data class ProductDToUpdate(
    var id: Long = 0,
    var name: String = "",
    var price: Double? = null,
    var weight: Float? = null,
    var image: List<Image>? = null,
    var description: String? = null,
    val category: String = ""
)