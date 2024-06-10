package org.example.admin.auth.model

import org.example.organization_city.model.DTO.CityOrganization

data class SingUpOrgRequest(
    var city: String? = null,
    var password: String? = null,
    var address: CityOrganization?,
    var name: String? = null,
    var phoneUser: String? = null,
)