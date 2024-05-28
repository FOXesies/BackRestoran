package org.example.service

import org.example.entity.Category.Category
import org.example.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired

class ServiceCategory {

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    fun getCategories(): List<Category> {
        return categoryRepository.findAll()
    }
}