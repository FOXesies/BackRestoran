package org.example.organization_city.service

import jakarta.transaction.Transactional
import org.example.organization_city.model.CityOrganization
import org.example.organization_city.repository.CityOrganizationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CityOrganizationService {

    @Autowired
    private lateinit var cityOrganizationRepository: CityOrganizationRepository
    @Transactional
    fun uniqueOrNew(cityName: String): CityOrganization {
        val city = cityOrganizationRepository.findByNameCity(cityName)
        println(city ?: CityOrganization(nameCity = cityName))
        return city ?: cityOrganizationRepository.save(CityOrganization(nameCity = cityName))
    }

}