package org.example.organization.repository;

import org.example.organization.model.Organization
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*


interface OrganizationRepository : JpaRepository<Organization, Long> {
    @Query(nativeQuery = true, value =  """
            SELECT DISTINCT o.*
            FROM organization o
            INNER JOIN organization_location_in_city olc ON o.id_organization = olc.organization_id_organization
            INNER JOIN location_organization lo ON lo.id_location = olc.location_in_city_id_location
            INNER JOIN city_organization co ON co.id_city = lo.city_id_city
            INNER JOIN organization_products op ON o.id_organization = op.organization_id_organization
            INNER JOIN product pr ON pr.id_product = op.products_id_product
            INNER JOIN category c ON c.id_category = pr.category_id_category
            WHERE co.name_city = :name_city AND c.name IN (:categories)
            """)
    fun findByCategory(name_city: String, categories: List<String>): List<Organization>
    @Query(nativeQuery = true, value = "SELECT DISTINCT o.*\n" +
            "FROM organization o\n" +
            "INNER JOIN organization_location_in_city olc ON o.id_organization = olc.organization_id_organization\n" +
            "INNER JOIN location_organization lo ON lo.id_location = olc.location_in_city_id_location\n" +
            "INNER JOIN city_organization co ON co.id_city = lo.city_id_city\n" +
            "WHERE co.name_city = :name_city")
    fun findByCity(name_city: String): List<Organization>
    fun findByName(name: String): Organization?

    @Query(nativeQuery = true, value = "select distinct co.name_city from organization o\n" +
            "inner join organization_location_in_city olc on o.id_organization = olc.organization_id_organization\n" +
            "inner join location_organization lo on lo.id_location = olc.location_in_city_id_location\n" +
            "inner join city_organization co on co.id_city = lo.city_id_city")
    fun getCities(): List<String>

    @Query(nativeQuery = true, value = """select distinct c.name from (select distinct * from organization o
        inner join organization_location_in_city olc on o.id_organization = olc.organization_id_organization
        inner join location_organization lo on lo.id_location = olc.location_in_city_id_location
        inner join city_organization co on co.id_city = lo.city_id_city where co.name_city = :name_city) res
        inner join organization_products op on res.id_organization = op.organization_id_organization
        inner join product pr on pr.id_product = op.products_id_product
        inner join category c on c.id_category = pr.category_id_category""")
    fun getAllUniqueCategories(name_city: String): List<String>

}
