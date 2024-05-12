package org.example.organization.service

import org.example.organization.model.DTO.OrganizationForUpdate
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
    fun getBasicinfo(idOrg: Long): OrganizationForUpdate{
        return MapperUtils.mapOrgInBasicInfo(repositoryOrganization.findById(idOrg).get())
    }
    fun insertOrganization(organization: Organization){
        repositoryOrganization.save(organization)
    }
    fun insertOrganization(organizations: List<Organization>){
        organizations.forEach{ organization -> repositoryOrganization.save(organization)}

    }
}