package org.example.favorite.controller

import org.example.favorite.entity.DTO.ResponseFavProduct
import org.example.favorite.service.FavoriteProductService
import org.example.organization.model.DTO.OrganizationDTO
import org.example.products.DTO.ResponseProduct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/favorite")
class ControllerFavorite {

    @Autowired
    private lateinit var favoriteProduct: FavoriteProductService

    @RequestMapping(path = ["/like_product/"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun likeProduct(@RequestBody favProduct: ResponseFavProduct) {
        return favoriteProduct.likeProduct(favProduct)
    }
    @RequestMapping(path = ["/like_organization/"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun likeOrg(@RequestBody favProduct: ResponseFavProduct) {
        return favoriteProduct.likeOrganization(favProduct)
    }
    @RequestMapping(path = ["/get_like_organization/"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getLikeOrg(@RequestBody favProduct: ResponseFavProduct): Boolean {
        return favoriteProduct.getLikeOrganization(favProduct)
    }
    @RequestMapping(path = ["/get_like_product/"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getLikeProduct(@RequestBody favProduct: ResponseFavProduct): Boolean {
        return favoriteProduct.getLikeProduct(favProduct)
    }
    @RequestMapping(path = ["/all_favorite_org/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllOrg(@PathVariable(value = "id") idUser: Long): List<OrganizationDTO> {
        return favoriteProduct.getAllLikeOrganization(idUser)
    }
    @RequestMapping(path = ["/all_favorite_product/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllProduct(@PathVariable(value = "id") idUser: Long): List<ResponseProduct> {
        return favoriteProduct.getALlLikeProduct(idUser)
    }
}