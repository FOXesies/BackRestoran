package org.example.repository

import org.example.entity.Basket.BasketItem
import org.springframework.data.jpa.repository.JpaRepository

interface BasketRepository : JpaRepository<BasketItem, Long> {
}