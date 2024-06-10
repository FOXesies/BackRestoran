package org.example.favorite.service

import org.example.favorite.entity.DTO.ResponseFavProduct
import org.example.favorite.entity.FavoriteOrganization
import org.example.favorite.entity.FavoriteProduct
import org.example.favorite.repository.FavoriteProductRepository
import org.example.feedbacks.service.FeedBacksService
import org.example.organization.model.DTO.OrganizationDTO
import org.example.organization.repository.OrganizationRepository
import org.example.products.DTO.ResponseProduct
import org.example.products.repository.ProductRepository
import org.example.repository.BasicUserRepository
import org.example.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FavoriteProductService {

    @Autowired
    private lateinit var repositoryFavProduct: FavoriteProductRepository

    @Autowired
    private lateinit var repositoryUser: BasicUserRepository

    @Autowired
    private lateinit var repositoryProduct: ProductRepository

    @Autowired
    private lateinit var organizationRepository: OrganizationRepository

    @Autowired
    private lateinit var feedBacksService: FeedBacksService

    fun likeProduct(response: ResponseFavProduct){
        val user = repositoryUser.findById(response.idUser).get()
        if(user.favoriteProducts.map { it.product.idProduct }.contains(response.idProduct)){
            user.favoriteProducts.removeIf { it.product.idProduct == response.idProduct }
        }
        else{

            user.favoriteProducts.add(FavoriteProduct(product = repositoryProduct.findById(response.idProduct).get()))
        }

        repositoryUser.save(user)
    }


    fun likeOrganization(response: ResponseFavProduct){
        val user = repositoryUser.findById(response.idUser).get()
        if(user.favoriteOrganization.map { it.organization.idOrganization }.contains(response.idOrg)){
            user.favoriteOrganization.removeIf { it.organization.idOrganization == response.idOrg }
        }
        else{
            user.favoriteOrganization.add(FavoriteOrganization(organization = organizationRepository.findById(response.idOrg).get()))
        }

        repositoryUser.save(user)
    }
    fun getLikeOrganization(response: ResponseFavProduct): Boolean{
        val user = repositoryUser.findById(response.idUser).get()
        return user.favoriteOrganization.map { it.organization.idOrganization }.contains(response.idOrg)
    }
    fun getLikeProduct(response: ResponseFavProduct): Boolean{
        val user = repositoryUser.findById(response.idUser).get()
        return user.favoriteProducts.map { it.product.idProduct }.contains(response.idProduct)
    }
    fun getAllLikeOrganization(idUser: Long): List<OrganizationDTO> {
        return repositoryUser.findById(idUser).get().favoriteOrganization.map { MapperUtils.mapOrganizationInDTO(it.organization!!, feedBacksService.getMiddleStar(it.organization.idOrganization!!)) }
    }
    fun getALlLikeProduct(idUser: Long): List<ResponseProduct> {
        return repositoryUser.findById(idUser).get().favoriteProducts.map { MapperUtils.mapResponseProductInResponseProduct(it.product) }
    }

}