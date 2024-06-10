package org.example.admin.auth.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.admin.auth.model.ResponseOrgAuth
import org.example.admin.auth.model.SingInOrgRequest
import org.example.admin.auth.model.SingUpOrgRequest
import org.example.organization.service.ServiceOrganization
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import organization.model.DTO.BasicInfoResponse
import java.io.IOException

@RestController
@RequestMapping("api/v1/admin/auth")
class AdminAuthOrg(private val organizationService: ServiceOrganization) {

    @RequestMapping(path = ["/sing-up"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun singUp(@RequestBody organization: SingUpOrgRequest): ResponseOrgAuth {
        return organizationService.singUp(organization)
    }
    @RequestMapping(path = ["/sing-in"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun singIn(@RequestBody organization: SingInOrgRequest): ResponseOrgAuth {
        return organizationService.singIn(organization)
    }

}