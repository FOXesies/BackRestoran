package org.example.favorite.service

import org.example.favorite.entity.DTO.ResponseFavProduct
import org.example.favorite.entity.FavoriteProduct
import org.example.favorite.repository.FavoriteProductRepository
import org.example.products.repository.ProductRepository
import org.example.repository.BasicUserRepository
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

    fun likeProduct(response: ResponseFavProduct){
        val user = repositoryUser.findById(response.idUser).get()
        user.favoriteProducts.add(FavoriteProduct(product = repositoryProduct.findById(response.idProduct).get()))
        repositoryUser.save(user)
    }
    fun dislikeProduct(response: ResponseFavProduct){
        val user = repositoryUser.findById(response.idUser).get()
        user.favoriteProducts.removeIf { it.product.idProduct == response.idProduct }
        repositoryUser.save(user)
    }

}