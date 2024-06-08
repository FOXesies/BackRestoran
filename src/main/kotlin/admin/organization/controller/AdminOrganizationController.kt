package org.example.admin.organization.controller

import org.example.admin.order.model.AdminStatusResponse
import org.example.organization.service.ServiceOrganization
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import organization.model.DTO.BasicInfoResponse

@RestController
@RequestMapping("api/v1/admin/organizations")
class AdminOrganizationController(private val organization: ServiceOrganization) {
    @RequestMapping(
        path = ["/update_info/"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun updateInfo(@RequestBody info: BasicInfoResponse): ResponseUpdate {
        return organization.updateBasicinfo(info)
    }

}

data class ResponseUpdate(
    val error_message: String? = null
)