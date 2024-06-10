package org.example.admin.auth.model

import org.example.organization_city.model.DTO.CityOrganization

data class ResponseOrgAuth(
    var message: String? = null,
    var idOrg: Long? = null
)
data class OrganizationResponse(
    var orgId: String? = null,
    var city: String? = null,
    var address: CityOrganization?,
    var name: String? = null,
    var phoneUser: String? = null,
)