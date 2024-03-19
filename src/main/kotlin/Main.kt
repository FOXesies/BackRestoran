package org.example

import org.example.entity.*
import org.example.repository.ProductRepository
import org.example.service.ServiceOrganization
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource


@SpringBootApplication
class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Main::class.java, *args)
        }
    }

    @Bean
    fun commandLineRunner(serviceOrganization: ServiceOrganization, productRepository: ProductRepository): CommandLineRunner {
        return CommandLineRunner {
            serviceOrganization.insertOrganization(
                listOf(
                    Organization(
                        name = "Ромашка",
                        address = "улица Московская 33",
                        phoneForUser = "+79008005522",
                        city = "Москва",
                        descriptions = "Лучший ресторан под названием \"Ромашка\"",
                        images = null /*listOf(
                            OrganizationImagesProfile(null,
                                "1.jpg"))*/,
                        category = listOf(
                            Category(
                                null, name = "Пицца", product = listOf(
                                    Product(
                                        name = "Пицца с Кока-колой",
                                        price = 100.0,
                                        weight = 100f,
                                        description = "Сильногазированая",
                                        imageProduct = null
                                    ),
                                    Product(
                                        name = "Пицца По-Царски",
                                        price = 450.0,
                                        weight = 400f,
                                        description = "20см диаметр",
                                        imageProduct = null
                                    )
                                )
                            ),
                            Category(
                                null, name = "Паста", product = listOf(
                                    Product(
                                        name = "Пицца с Кока-колой",
                                        price = 100.0,
                                        weight = 100f,
                                        description = "Сильногазированая",
                                        imageProduct = null
                                    ),
                                    Product(
                                        name = "Пицца По-Царски",
                                        price = 450.0,
                                        weight = 400f,
                                        description = "20см диаметр",
                                        imageProduct = null
                                    )
                                )
                            )
                        ),
                        ratings = listOf(
                            Rating(rating = 4),
                            Rating(rating = 2),
                            Rating(rating = 5),
                            Rating(rating = 4),
                        )
                    ),
                    Organization(
                        name = "Бургер кинг",
                        address = "проспект Ленина 40",
                        phoneForUser = "+79205748652",
                        city = "Санкт-Петербург",
                        descriptions = "Маркетнг - это не для нас",
                        images = listOf(OrganizationImagesProfile(null,
                                "1.jpg")),
                        category = listOf(
                            Category(
                                null, name = "Пицца", product = listOf(
                                    Product(
                                        name = "Пицца без Кока-колой",
                                        price = 100.0,
                                        weight = 100f,
                                        description = "Сильногазированая",
                                        imageProduct = null
                                    ),
                                    Product(
                                        name = "Пицца По-Холопски",
                                        price = 450.0,
                                        weight = 400f,
                                        description = "20см диаметр",
                                        imageProduct = null
                                    )
                                )
                            ),
                            Category(
                                null, name = "Супы", product = listOf(
                                    Product(
                                        name = "Борщ",
                                        price = 100.0,
                                        weight = 100f,
                                        description = "со сметаной",
                                        imageProduct = null
                                    )
                                )
                            )
                        ),
                        ratings = null
                    )
                )
            )
        }
    }
}