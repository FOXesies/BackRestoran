package org.example.organization_city.service

import jakarta.transaction.Transactional
import org.example.organization_city.model.LocationOrganization
import org.example.organization_city.repository.LocationOrganizationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LocationorganizationService {

    @Autowired
    private lateinit var locationOrganizationRepository: LocationOrganizationRepository

    @Autowired
    private lateinit var cityOrganizationService: CityOrganizationService

    @Transactional
    fun insert(locationOrganization: LocationOrganization): LocationOrganization {
        return locationOrganizationRepository.save(locationOrganization)
    }

}