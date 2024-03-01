package org.example.repository;

import org.example.entity.Organization
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrganizationRepository : JpaRepository<Organization, Long> {

}