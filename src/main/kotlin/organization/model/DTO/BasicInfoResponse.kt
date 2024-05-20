package organization.model.DTO

import org.example.organization.model.DTO.CityOrganization

data class BasicInfoResponse(
    val idOrg: Long, val name: String, val phone: String, val description: String, val locationAll: Map<String, List<CityOrganization>>
)