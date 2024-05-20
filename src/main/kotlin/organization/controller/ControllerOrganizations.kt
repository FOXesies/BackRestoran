package org.example.organization.controller

import jakarta.servlet.http.HttpServletResponse
import org.example.organization.model.DTO.OrganizationDTO
import org.example.organization.model.DTO.OrganizationIdDTO
import org.example.organization.service.ServiceOrganization
import org.example.utils.MapperUtils.Companion.mapOrganizationIdInDTO
import org.example.utils.MapperUtils.Companion.mapOrganizationInDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.InputStreamResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.*
import organization.model.DTO.BasicInfoResponse


@RestController
@RequestMapping("api/v1/organizations")
class ControllerOrganizations {

    @Autowired
    lateinit var serviceOrganization: ServiceOrganization

    @RequestMapping(
        path = ["/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun getOrganizationById(@PathVariable(value = "id") idOrganization: Long): OrganizationIdDTO {
        val organization = serviceOrganization.repositoryOrganization.findById(idOrganization)
        return mapOrganizationIdInDTO(organization.get())
    }

    @RequestMapping(
        path = ["/"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun getOrganizations(@RequestParam city: String): List<OrganizationDTO> {
        val organizations = serviceOrganization.getOrganizations(city)

        return organizations.map { organization ->
            mapOrganizationInDTO(organization)
        }
    }

    @RequestMapping(
        path = ["/cities"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun getCities(): List<String> {
        return serviceOrganization.getCities()
    }

    //ADMIN FUNC
    @RequestMapping(
        path = ["/get_info/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun getBasicInfo(@PathVariable(value = "id") idOrg: Long): OrganizationIdDTO {
        return serviceOrganization.getBasicinfo(idOrg)
    }

    @RequestMapping(
        path = ["/update_info/"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun updateBasicInfo(@RequestBody order: BasicInfoResponse) {
        return serviceOrganization.updateBasicinfo(order)
    }



    @RequestMapping(
        path = ["organizations_images/{path}"],
        method = [RequestMethod.GET],
        produces = [MediaType.IMAGE_JPEG_VALUE]
    )
    fun uploadImageByPath(
        response: HttpServletResponse,
        @PathVariable(value = "path") pathImage: String
    ): ResponseEntity<InputStreamResource> {
        val imgFile = ClassPathResource("organizations_images/$pathImage")
        StreamUtils.copy(imgFile.inputStream, response.outputStream)

        return ResponseEntity
            .ok()
            .contentType(MediaType.IMAGE_JPEG)
            .body(InputStreamResource(imgFile.getInputStream()));
    }
}
