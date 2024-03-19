package org.example.controller

import jakarta.servlet.http.HttpServletResponse
import org.example.DTO.OrganizationDTO
import org.example.DTO.OrganizationIdDTO
import org.example.service.ServiceOrganization
import org.example.utils.MapperUtils.Companion.mapOrganizationIdInDTO
import org.example.utils.MapperUtils.Companion.mapOrganizationInDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.InputStreamResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.*
import java.io.IOException
import java.util.*


@RestController
@RequestMapping("api/v1/organizations")
class ControllerOrganizations {

    @Autowired
    lateinit var serviceOrganization: ServiceOrganization

    @RequestMapping(path = ["/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.IMAGE_JPEG_VALUE])
    @ResponseBody
    fun getOrganizationById(@PathVariable(value = "id") idOrganization: Long): OrganizationIdDTO {
        val organization = serviceOrganization.repositoryOrganization.findById(idOrganization)
        return mapOrganizationIdInDTO(organization.get())
    }
    @RequestMapping(path = ["/"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.IMAGE_JPEG_VALUE])
    @ResponseBody
    fun getOrganizations(): List<OrganizationDTO> {
        val organizations = serviceOrganization.getOrganizations()

        return organizations.map { organization ->
            mapOrganizationInDTO(organization)
        }
    }

/*    @RequestMapping(value = ["/iamge{id}"], method = [RequestMethod.GET], produces = [MediaType.IMAGE_JPEG_VALUE])
    @Throws(
        IOException::class
    )
    fun getImage(response: HttpServletResponse, @PathVariable(value = "id") idOrganization: Long): ResponseEntity<InputStreamResource> {

        StreamUtils.copy(imgFile.inputStream, response.outputStream)

        return ResponseEntity
            .ok()
            .contentType(MediaType.IMAGE_JPEG)
            .body(InputStreamResource(imgFile.getInputStream()));
    }*/
}
