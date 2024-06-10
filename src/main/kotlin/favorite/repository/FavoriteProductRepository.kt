package org.example.favorite.repository;

import org.example.favorite.entity.FavoriteProduct
import org.springframework.data.jpa.repository.JpaRepository

interface FavoriteProductRepository : JpaRepository<FavoriteProduct, Long> {

}