package org.example.products.controller

import org.example.products.DTO.ResponseProduct
import org.example.products.entity.Product
import org.example.products.service.ServiceProduct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/v1/products")
class ControllerProducts {

    @Autowired
    lateinit var serviseProduct: ServiceProduct

    @RequestMapping(path = ["/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun getFullInfoProduct(@PathVariable(value = "id") idOrganization: Long): Product {
        val product = serviseProduct.getProduct(idOrganization)
        return product
    }

    @RequestMapping(path = ["/get_info/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun getBasicInfoproduct(@PathVariable(value = "id") idOrganization: Long): ResponseProduct {
        return serviseProduct.getProductBasic(idOrganization)
    }

/*    @PostMapping("/add_product/")
    fun updateProduct(@RequestParam("image") file: MultipartFile,
                      @RequestParam("product") product: ResponseProduct
    ): ResponseEntity<Any> {
        try {
            serviseProduct.addproduct(product, file)
            return ResponseEntity.ok().build()
        } catch (e: IOException) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }*/
}