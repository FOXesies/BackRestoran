package org.example.organization.repository;

import org.example.organization.model.Organization
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*


interface OrganizationRepository : JpaRepository<Organization, Long> {
    @Query(nativeQuery = true, value = "select * from organization where id_organization in (select DISTINCT organization_id_organization\n" +
            "from organization_locations_all loc\n" +
            "inner join city_organization city on loc.locations_all_id_city = city.id_city\n" +
            "where city.name_city = :nameCity )")
    fun findByCategory(nameCity: String): List<Organization>
    @Query(nativeQuery = true, value = "select distinct name_city from city_organization")
    fun getCities(): List<String>

}
