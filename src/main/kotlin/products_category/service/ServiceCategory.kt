package org.example.products_category.service

import jakarta.transaction.Transactional
import org.example.organization_city.model.CityOrganization
import org.example.products_category.entity.Category
import org.example.products_category.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ServiceCategory {

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    fun getCategories(): List<Category> {
        return categoryRepository.findAll()
    }

    @Transactional
    fun uniqueOrNew(name: String): Category {
        val category = categoryRepository.findByName(name)
        println(category ?: Category(name = name))
        return category ?: categoryRepository.save(Category(name = name))
    }
}