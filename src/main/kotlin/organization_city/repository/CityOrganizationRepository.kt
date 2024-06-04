package org.example.organization_city.repository;

import org.example.organization_city.model.CityOrganization
import org.springframework.data.jpa.repository.JpaRepository

interface CityOrganizationRepository : JpaRepository<CityOrganization, Long> {
    fun findByNameCity(nameCity: String): CityOrganization?
}