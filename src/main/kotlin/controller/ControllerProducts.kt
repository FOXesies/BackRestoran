package org.example.controller

import org.example.entity.Product.Product
import org.example.service.ServiceProduct
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
}