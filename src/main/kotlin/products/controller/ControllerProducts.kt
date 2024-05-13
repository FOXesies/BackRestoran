package org.example.products.controller

import org.example.products.entity.Product
import org.example.products.DTO.ProductResponse
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
    fun getOrganizationById(@PathVariable(value = "id") idOrganization: Long): Product {
        val product = serviseProduct.getProduct(idOrganization)
        return product
    }


    @RequestMapping(
        path = ["/get_info/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun getBasicInfo(@PathVariable(value = "id") idProduct: Long): Product {
        return serviseProduct.getBasicinfo(idProduct)
    }
    @RequestMapping(
        path = ["/update_info/"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun updateBasicInfo(@RequestBody product: Product) {
        return serviseProduct.updateBasicinfo(product)
    }
}