package org.example.utils

import org.example.DTO.Category.CategoryDTO
import org.example.DTO.Organization.OrganizationDTO
import org.example.DTO.Organization.OrganizationIdDTO
import org.example.entity.Category.Category
import org.example.entity.Organization.Organization
import org.example.service.ImageSearchUtils
import org.springframework.stereotype.Service

@Service
class MapperUtils {
    companion object {
        fun mapOrganizationInDTO(organization: Organization): OrganizationDTO {
            return OrganizationDTO(
                organization.idOrganization,
                organization.name,
                organization.address,
                organization.phoneForUser,
                organization.city,
                organization.idImage,
                organization.descriptions,
                organization.category.map{ mapCategoryDTO(it) },
                if(organization.ratings.isEmpty()) -1.0 else organization.ratings.map { it.rating }.average(),
                organization.ratings.size ,
                organization.images?.map { ImageSearchUtils.getInputStream(it.data) }
            )
        }
        fun mapOrganizationIdInDTO(organization: Organization): OrganizationIdDTO {
            return OrganizationIdDTO(
                organization.idOrganization,
                organization.name,
                organization.address,
                organization.phoneForUser,
                organization.city,
                organization.idImage,
                organization.descriptions,
                organization.category.map { it.name },
                organization.category.associateBy ({ it.name }, {it.product}),
                if(organization.ratings.isNotEmpty()) organization.ratings.map { it.rating }.average() else 0.0,
                organization.ratings.size ,
                organization.user != null,
                organization.images?.map { ImageSearchUtils.getInputStream(it.data) } //organization.images?.map { ImageSearchUtils.getInputStream(it) }
            )
        }

        fun mapCategoryDTO(category: Category): CategoryDTO {
            return CategoryDTO(
                category.name,
                category.images
            )
        }
    }

}