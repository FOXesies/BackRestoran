package org.example.utils

import org.example.DTO.CategoryDTO
import org.example.DTO.OrganizationDTO
import org.example.DTO.OrganizationIdDTO
import org.example.entity.Category
import org.example.entity.Organization
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
                organization.descriptions,
                organization.category.map{ mapCategoryDTO(it) },
                if(organization.ratings == null || organization.ratings!!.isEmpty()) -1.0 else organization.ratings?.map { it.rating }?.average(),
                organization.ratings?.map { it.rating }?.count(),
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
                organization.descriptions,
                organization.category.map { it.name },
                organization.category.associateBy ({ it.name }, {it.product}),
                if(organization.ratings != null) organization.ratings?.map { it.rating }?.average() else -1.0,
                organization.ratings?.map { it.rating }?.count(),
                organization.images?.map { ImageSearchUtils.getInputStream(it.data) } //organization.images?.map { ImageSearchUtils.getInputStream(it) }
            )
        }

        fun mapCategoryDTO(category: Category): CategoryDTO{
            return CategoryDTO(
                category.name,
                category.images
            )
        }
    }

}