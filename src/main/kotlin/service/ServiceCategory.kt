package org.example.service

import org.example.entity.Category
import org.springframework.beans.factory.annotation.Autowired
import repository.CategoryRepository

class ServiceCategory {

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    fun getCategories(): List<Category> {
        return categoryRepository.findAll()
    }
}