package org.example.organization.service

import jakarta.transaction.Transactional
import org.example.products_category.entity.Category
import org.example.feedbacks.service.FeedBacksService
import org.example.organization_city.model.CityOrganization
import org.example.organization.model.DTO.OrganizationIdDTO
import org.example.organization_city.model.LocationOrganization
import org.example.organization.model.Organization
import org.example.organization.repository.OrganizationRepository
import org.example.organization_city.service.CityOrganizationService
import org.example.organization_city.service.LocationorganizationService
import org.example.products.entity.Product
import org.example.products.repository.ProductRepository
import org.example.products.service.ServiceProduct
import org.example.products_category.repository.CategoryRepository
import org.example.products_category.service.ServiceCategory
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

    @Autowired
    lateinit var productServiceCategory: ServiceCategory

    @Autowired
    lateinit var feedBacksService: FeedBacksService

    @Autowired
    lateinit var productService: ServiceProduct

    @Autowired
    lateinit var locationService: LocationorganizationService

    @Autowired
    private lateinit var cityOrganizationService: CityOrganizationService

    fun getOrganizations(category: String): List<Organization> {
        return repositoryOrganization.findByCategory(category)
    }
    fun getCities(): List<String> {
        return repositoryOrganization.getCities()
    }

    //FUNC ADMIN
    fun getBasicinfo(idOrg: Long): OrganizationIdDTO {
        return MapperUtils.mapOrganizationIdInDTO(
            repositoryOrganization.findById(idOrg).get(), feedBacksService.getMiddleStar(idOrg)
        )
    }
    fun updateBasicinfo(orgUpdate: BasicInfoResponse) {
        val org = repositoryOrganization.findById(orgUpdate.idOrg).get()
        org.name = orgUpdate.name
        org.descriptions = orgUpdate.description

        org.locationInCity = orgUpdate.locationAll.flatMap { entry ->
            entry.value.map { location ->
                locationService.insert(LocationOrganization(
                    city = CityOrganization(nameCity = entry.key),
                    address = location.address,
                    lat = location.points!!.latitude,
                    lon = location.points.longitude
                ))
            }
        }.toMutableList()
        // Создаем новую изменяемую коллекцию для locationsAll
        /*org.locationsAll = orgUpdate.locationAll.map { entry ->
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
        }.toMutableList() // Преобразуем в изменяемую коллекцию*/

        org.phoneForUser = orgUpdate.phone
        repositoryOrganization.save(org)
    }

    @Transactional
    fun insertOrganization(organization: Organization) {
        organization.locationInCity.forEach { locationOrganization ->
            locationOrganization.city = cityOrganizationService.uniqueOrNew(locationOrganization.city.nameCity!!)
            locationService.insert(locationOrganization)
        }
        organization.products.forEach { product ->
            product.category = productServiceCategory.uniqueOrNew(product.category.name)
            productService.insert(product)
        }

        repositoryOrganization.save(organization)
    }
    fun insertOrganization(organizations: List<Organization>){
        organizations.forEach{ organization -> insertOrganization(organization)}
    }

    fun getCategories(): List<String> {
        return repositoryOrganization.getAllUniqueCategories()
    }

    fun insertProduct(idOrg: Long, product: Product, category: String){
        val organization = repositoryOrganization.findById(idOrg).get()
        product.category = productServiceCategory.uniqueOrNew(product.category.name)
        organization.products.add(productService.insert(product))

        repositoryOrganization.save(organization)
    }
}