package organization.model.DTO

import org.example.entity.Image
import org.example.organization_city.model.DTO.CityOrganization

data class BasicInfoResponse(
    val idOrg: Long? = null,
    val name: String? = null,
    val phone: String? = null,
    val description: String? = null,
    val locationAll: Map<String, List<CityOrganization>>? = null,
    var idImages: List<ImageDTO>? = null
)

data class ImageDTO(
    val id: Long? = null,
    val value: ByteArray? = null,
    var main: Boolean = false
)