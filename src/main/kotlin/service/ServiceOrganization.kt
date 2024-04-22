package org.example.service

import org.example.entity.Organization.Organization
import org.example.repository.OrganizationRepository
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

    fun insertOrganization(organization: Organization){
        repositoryOrganization.save(organization)
    }
    fun insertOrganization(organizations: List<Organization>){
        organizations.forEach{ organization -> repositoryOrganization.save(organization)}

    }
}