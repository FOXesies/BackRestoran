package org.example.basket.repository

import org.example.basket.entity.BasketItem
import org.springframework.data.jpa.repository.JpaRepository

interface BasketRepository : JpaRepository<BasketItem, Long> {
}