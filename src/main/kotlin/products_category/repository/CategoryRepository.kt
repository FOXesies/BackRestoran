package org.example.products_category.repository;

import org.example.products_category.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long> {
    fun findByName(name: String): Category?
}
