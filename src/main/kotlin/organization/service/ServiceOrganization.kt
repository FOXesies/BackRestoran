package org.example.organization.service

import entity.Users_.UsersRepository
import jakarta.transaction.Transactional
import org.example.admin.organization.controller.ResponseUpdate
import org.example.admin.products.model.BasicInfoResponseADD
import org.example.entity.Image
import org.example.feedbacks.service.FeedBacksService
import org.example.organization.model.DTO.FiltercategoryOrg
import org.example.organization.model.DTO.OrganizationIdDTO
import org.example.organization.model.Organization
import org.example.organization.repository.OrganizationRepository
import org.example.organization_city.model.LocationOrganization
import org.example.organization_city.service.CityOrganizationService
import org.example.organization_city.service.LocationorganizationService
import org.example.products.DTO.ResponseProduct
import org.example.products.entity.Product
import org.example.products.service.ServiceProduct
import org.example.products_category.service.ServiceCategory
import org.example.repository.BasicUserRepository
import org.example.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import organization.model.DTO.BasicInfoResponse

@Service
class ServiceOrganization {

    @Autowired
    lateinit var repositoryOrganization: OrganizationRepository

    @Autowired
    private lateinit var productServiceCategory: ServiceCategory

    @Autowired
    private lateinit var feedBacksService: FeedBacksService

    @Autowired
    private lateinit var productService: ServiceProduct

    @Autowired
    private lateinit var locationService: LocationorganizationService

    @Autowired
    private lateinit var cityOrganizationService: CityOrganizationService
    @Autowired
    private lateinit var usersRepository: BasicUserRepository

    fun getOrganizations(city: String): List<Organization> {
        return repositoryOrganization.findByCity(city)
    }
    fun getinfo(idOrg: Long, city: String): OrganizationIdDTO {
        return MapperUtils.mapOrganizationIdInDTOHome(
            repositoryOrganization.findById(idOrg).get(), city, feedBacksService.getMiddleStar(idOrg)
        )
    }
    fun getOrganizationsByCategory(filtercategoryOrg: FiltercategoryOrg): List<Organization> {
        return repositoryOrganization.findByCategory(filtercategoryOrg.city, filtercategoryOrg.categories)
    }
    fun getCities(): List<String> {
        return repositoryOrganization.getCities()
    }
    fun getAddresses(city: String, idOrg: Long): List<LocationOrganization>{
        return repositoryOrganization.findById(idOrg).get().locationInCity.filter { it.city.nameCity == city }
    }
    fun getByUserOrg(idUser: Long): Long?{
        return repositoryOrganization.findAll().first{
            it.user.profileUUID == idUser
        }.idOrganization!!
    }

    //FUNC ADMIN
    fun getBasicinfo(idOrg: Long): OrganizationIdDTO {
        return MapperUtils.mapOrganizationIdInDTO(
            repositoryOrganization.findById(idOrg).get(), feedBacksService.getMiddleStar(idOrg)
        )
    }
    fun getBasicinfoForAdmin(idOrg: Long): BasicInfoResponse {
        return MapperUtils.mapOrgToBasicInfo(
            repositoryOrganization.findById(idOrg).get()
        )
    }
    fun updateBasicinfo(orgUpdate: BasicInfoResponse, listd: List<MultipartFile>): ResponseUpdate {
        val org = repositoryOrganization.findById(orgUpdate.idOrg).get()
        if(org.name != orgUpdate.name){
            val org_test = repositoryOrganization.findByName(orgUpdate.name!!)
                if(org_test != null){
                    return ResponseUpdate("Имя уже занято")
                }
        }
        org.name = orgUpdate.name
        org.descriptions = orgUpdate.description
        org.phoneForUser = orgUpdate.phone!!

        for (cityName in orgUpdate.locationAll!!.keys) {
            val city = cityOrganizationService.uniqueOrNew(cityName)
            for (location in orgUpdate.locationAll[cityName]!!) {
                if (!org.locationInCity.map { OrgCityAnLoc(it.city.nameCity, it.address, it.lat, it.lon) }
                    .contains(OrgCityAnLoc(city.nameCity, location.address, location.points!!.latitude, location.points.longitude))) {

                    org.locationInCity.add(LocationOrganization(city = city, lat = location.points!!.latitude, lon = location.points.longitude, address = location.address))
                }
            }
        }

        org.idImages.clear()
        org.idImages.addAll(orgUpdate.idImages?.mapIndexed { index, image -> Image(value = listd[index].bytes, main = image.main) }?.toMutableList()?: mutableListOf())

        repositoryOrganization.save(org)
        return ResponseUpdate(null)
    }
     fun addBasicinfo(orgUpdate: BasicInfoResponseADD, listd: List<MultipartFile>): ResponseUpdate {
         val org_test = repositoryOrganization.findByName(orgUpdate.name!!)
         if(org_test != null){
             return ResponseUpdate("Имя уже занято")
         }

        val org = Organization(
            name = orgUpdate.name,
            products = mutableListOf(),
            phoneForUser = orgUpdate.phone,
            user = usersRepository.findById(orgUpdate.idUser).get(),
            descriptions = orgUpdate.description,
        )

        for (cityName in orgUpdate.locationAll!!.keys) {
            val city = cityOrganizationService.uniqueOrNew(cityName)
            for (location in orgUpdate.locationAll[cityName]!!) {
                if (!org.locationInCity.map { OrgCityAnLoc(it.city.nameCity, it.address, it.lat, it.lon) }
                    .contains(OrgCityAnLoc(city.nameCity, location.address, location.points!!.latitude, location.points.longitude))) {

                    org.locationInCity.add(LocationOrganization(city = city, lat = location.points!!.latitude, lon = location.points.longitude, address = location.address))
                }
            }
        }

        org.idImages.clear()
        org.idImages.addAll(orgUpdate.idImages?.mapIndexed { index, image -> Image(value = listd[index].bytes, main = image.main) }?.toMutableList()?: mutableListOf())

        repositoryOrganization.save(org)
        return ResponseUpdate(null)
    }

    @Transactional
    fun insertOrganization(organization: Organization) {
        organization.locationInCity.forEach { locationOrganization ->
            locationOrganization.city = cityOrganizationService.uniqueOrNew(locationOrganization.city.nameCity!!)
            locationService.insert(locationOrganization)
        }
        organization.products.forEach { product ->
            product.category = productServiceCategory.uniqueOrNew(product.category!!.name)
            productService.insert(product)
        }

        repositoryOrganization.save(organization)
    }
    fun insertOrganization(organizations: List<Organization>){
        organizations.forEach{ organization -> insertOrganization(organization)}
    }

    fun getCategories(city: String): List<String> {
        return repositoryOrganization.getAllUniqueCategories(city)
    }

    fun getCategoriesByOrg(id: Long): List<String>? {
        return repositoryOrganization.findById(id).get().products.map { it.category!!.name }.distinct()
    }

    fun insertProduct(idOrg: Long, product: Product, category: String){
        val organization = repositoryOrganization.findById(idOrg).get()
        product.category = productServiceCategory.uniqueOrNew(product.category!!.name)
        organization.products.add(productService.insert(product))

        repositoryOrganization.save(organization)
    }

    fun getAllProducts(idOrg: Long): Map<String, List<ResponseProduct>> {
        return  repositoryOrganization.findById(idOrg).get().products.groupBy(keySelector = {it.category!!.name},
            valueTransform = {MapperUtils.mapResponseProductInResponseProduct(it)})
    }


}

data class OrgCityAnLoc(
    val city: String?,
    val address: String?,
    val lat: Double?,
    val lon: Double?,
)