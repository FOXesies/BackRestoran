package org.example.organization.service

import org.example.entity.Category.Category
import org.example.organization.model.CityOrganization
import org.example.organization.model.DTO.OrganizationIdDTO
import org.example.organization.model.LocationOrganization
import org.example.organization.model.Organization
import org.example.organization.repository.OrganizationRepository
import org.example.products.entity.Product
import org.example.repository.CategoryRepository
import org.example.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import organization.model.DTO.BasicInfoResponse

@Service
class ServiceOrganization {

    @Autowired
    lateinit var repositoryOrganization: OrganizationRepository

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    fun getOrganizations(category: String): List<Organization> {
        return repositoryOrganization.findByCategory(category)
    }
    fun getCities(): List<String> {
        return repositoryOrganization.getCities()
    }

    //FUNC ADMIN
    fun getBasicinfo(idOrg: Long): OrganizationIdDTO {
        return MapperUtils.mapOrganizationIdInDTO(repositoryOrganization.findById(idOrg).get())
    }
    fun updateBasicinfo(orgUpdate: BasicInfoResponse) {
        val org = repositoryOrganization.findById(orgUpdate.idOrg).get()
        org.name = orgUpdate.name
        org.descriptions = orgUpdate.description

        // Создаем новую изменяемую коллекцию для locationsAll
        org.locationsAll = orgUpdate.locationAll.map { entry ->
            CityOrganization(
                nameCity = entry.key,
                locationInCity = entry.value.map { location ->
                    LocationOrganization(
                        address = location.address,
                        lat = location.points!!.latitude,
                        lon = location.points.longitude
                    )
                }.toMutableList() // Преобразуем в изменяемую коллекцию
            )
        }.toMutableList() // Преобразуем в изменяемую коллекцию

        org.phoneForUser = orgUpdate.phone
        repositoryOrganization.save(org)
    }


    fun insertOrganization(organization: Organization){
        repositoryOrganization.save(organization)
    }
    fun insertOrganization(organizations: List<Organization>){
        organizations.forEach{ organization -> repositoryOrganization.save(organization)}
    }

    fun getCategories(): List<String> {
        return repositoryOrganization.getAllUniqueCategories()
    }

    fun insertProduct(idOrg: Long, product: Product, category: String){
        val organization = repositoryOrganization.findById(idOrg).get()
        organization.category.find { it.name == category }.let {
            it?.product?.add(product) ?: categoryRepository.save(Category(name = category, product = mutableListOf(product)))
        }
        repositoryOrganization.save(organization)
    }
}