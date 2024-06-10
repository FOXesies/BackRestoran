package org.example.organization.controller

import jakarta.servlet.http.HttpServletResponse
import org.example.feedbacks.entity.FeedBacks
import org.example.feedbacks.entity.FeedbacksDTO
import org.example.feedbacks.service.FeedBacksService
import org.example.organization.model.DTO.FiltercategoryOrg
import org.example.organization.model.DTO.OrganizationDTO
import org.example.organization.model.DTO.OrganizationIdDTO
import org.example.organization.service.ServiceOrganization
import org.example.organization_city.model.LocationOrganization
import org.example.products.DTO.ResponseProduct
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

    @Autowired
    lateinit var feedBacksService: FeedBacksService

    @RequestMapping(
        path = ["/{id}/"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun getOrganizationById(@PathVariable(value = "id") idOrganization: Long, @RequestParam city: String): OrganizationIdDTO {
        return serviceOrganization.getinfo(idOrganization, city)
    }

    @RequestMapping(
        path = ["/"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun getOrganizations(@RequestParam city: String): List<OrganizationDTO> {
        val organizations = serviceOrganization.getOrganizations(city)

        return organizations.map { organization ->
            mapOrganizationInDTO(organization, feedBacksService.getMiddleStar(organization.idOrganization!!))
        }
    }

    @RequestMapping(
        path = ["/filter"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun getOrganizationsByCategory(@RequestBody filtercategoryOrg: FiltercategoryOrg): List<Long> {
        val organizations = serviceOrganization.getOrganizationsByCategory(filtercategoryOrg)

        return organizations.map { organization ->
            organization.idOrganization!!
        }
    }

    @RequestMapping(
        path = ["/feedbacks/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun getFeedbacks(@PathVariable(value = "id") idOrganization: Long): List<FeedbacksDTO> {
        return feedBacksService.getAllfeedbacksByOrg(idOrganization)
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
    fun getBasicInfo(@PathVariable(value = "id") idOrg: Long): BasicInfoResponse {
        return serviceOrganization.getBasicinfoForAdmin(idOrg)
    }
    @RequestMapping(
        path = ["/categories"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun getCategories(@RequestParam city: String): List<String> {
        return serviceOrganization.getCategories(city)
    }
    @RequestMapping(
        path = ["/get_org_user/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun getOrgByUser(@PathVariable(value = "id") id: Long): Long? {
        return serviceOrganization.getByUserOrg(id)
    }
    @RequestMapping(
        path = ["/categories/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun getCategoriesInOrg(@PathVariable(value = "id") id: Long): List<String>? {
        return serviceOrganization.getCategoriesByOrg(id)
    }
    @RequestMapping(
        path = ["/addresses/{id}/"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun getAddresses(@PathVariable(value = "id") idOrg: Long, @RequestParam city: String): List<LocationOrganization> {
        return serviceOrganization.getAddresses(city, idOrg)
    }
    @RequestMapping(
        path = ["/all_products/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun getAllProdubct(@PathVariable(value = "id") idOrg: Long): Map<String, List<ResponseProduct>> {
        return serviceOrganization.getAllProducts(idOrg)
    }


}
