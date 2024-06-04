package org.example.organization_city.repository;

import org.example.organization_city.model.LocationOrganization
import org.springframework.data.jpa.repository.JpaRepository

interface LocationOrganizationRepository : JpaRepository<LocationOrganization, Long> {
}