package org.example.service

import org.example.entity.Organization
import org.example.repository.OrganizationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ServiceOrganization {

    @Autowired
    lateinit var repositoryOrganization: OrganizationRepository

    fun getOrganizations(pageable: Pageable): Page<Organization> {
        return repositoryOrganization.findAll(pageable)
    }


    fun insertOrganization(organization: Organization){
        repositoryOrganization.save(organization)
    }
}