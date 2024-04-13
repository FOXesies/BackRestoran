package org.example.repository;

import org.example.entity.Organization.Organization
import org.springframework.data.jpa.repository.JpaRepository

interface OrganizationRepository : JpaRepository<Organization, Long> {

}
