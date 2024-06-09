package org.example.admin.organization.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.admin.order.model.AdminStatusResponse
import org.example.admin.products.controller.ProductDToUpdate
import org.example.organization.service.ServiceOrganization
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import organization.model.DTO.BasicInfoResponse
import java.io.IOException

@RestController
@RequestMapping("api/v1/admin/organizations")
class AdminOrganizationController(private val organizationService: ServiceOrganization) {

    @PostMapping("/update_info/")
    fun updateOrgImage(@RequestParam("image") file: List<MultipartFile>,
                       @RequestParam("organization") organization: String): ResponseEntity<Any> {
        try {
            println(organization)
            val objectMapper = ObjectMapper()
            val product: BasicInfoResponse = objectMapper.readValue(organization, BasicInfoResponse::class.java)
            println(product.name)
            organizationService.updateBasicinfo(product, file)
            return ResponseEntity.ok().build()
        } catch (e: IOException) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}

data class ResponseUpdate(
    val error_message: String? = null
)