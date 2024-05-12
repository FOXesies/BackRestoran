package org.example.organization.model.DTO

import org.example.entity.Image
import java.io.InputStream

data class OrganizationForUpdate(
    val idorganization: Long? = null,
    val name: String,
    val idImage: Long? = null,
)