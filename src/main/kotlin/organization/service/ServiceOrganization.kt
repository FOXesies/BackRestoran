package org.example.organization.service

import org.example.organization.model.DTO.OrganizationIdDTO
import org.example.organization.model.Organization
import org.example.organization.repository.OrganizationRepository
import org.example.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ServiceOrganization {

    @Autowired
    lateinit var repositoryOrganization: OrganizationRepository

    fun getOrganizations(category: String): List<Organization> {
        return repositoryOrganization.findByCategory(category)
    }
    fun getCities(): List<String> {
        return repositoryOrganization.getCities()
    }


    //FUNC ADMIN
    fun getBasicinfo(idOrg: Long): OrganizationIdDTO {
        return MapperUtils.mapOrganizationIdInDTO(repositoryOrganization.findById(idOrg).get())
    }
    fun updateBasicinfo(orgUpdate: OrganizationIdDTO) {
        val org = repositoryOrganization.findById(orgUpdate.idOrganization).get()
        org.name = orgUpdate.name
        repositoryOrganization.save(org)
    }


    fun insertOrganization(organization: Organization){
        repositoryOrganization.save(organization)
    }
    fun insertOrganization(organizations: List<Organization>){
        organizations.forEach{ organization -> repositoryOrganization.save(organization)}
    }
}