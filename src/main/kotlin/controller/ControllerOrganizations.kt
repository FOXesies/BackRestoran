package org.example.controller

import org.example.entity.Organization
import org.example.repository.OrganizationRepository
import org.example.service.ServiceOrganization
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/organizations")
class ControllerOrganizations {

    @Autowired
    lateinit var serviceOrganization: ServiceOrganization

    @RequestMapping(path = ["/please"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getOrganizations(): Page<Organization> {
        return serviceOrganization.getOrganizations(PageRequest.of(0, 2))
    }

}
