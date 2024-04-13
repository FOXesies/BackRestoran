package org.example

import org.example.entity.Category.Category
import org.example.entity.Image
import org.example.entity.Organization.Organization
import org.example.entity.Organization.Rating
import org.example.entity.Organization.Test
import org.example.entity.Product.Product
import org.example.repository.ProductRepository
import org.example.service.ImageSearchUtils
import org.example.service.ServiceOrganization
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Main::class.java, *args)
        }
    }

    @Bean
    fun commandLineRunner(serviceOrganization: ServiceOrganization, imageService: ImageSearchUtils, productRepository: ProductRepository): CommandLineRunner {
        return CommandLineRunner {

            imageService.imageImpl.save(Image(id = 1, path = "organizations_images\\1.jpg"))
            imageService.imageImpl.save(Image(id = 2, path = "C:\\Users\\User\\Pictures\\2.png"))
            imageService.imageImpl.save(Image(id = 3, path = "C:\\Users\\User\\Pictures\\11.png"))

                var items = listOf(
                    Organization(
                        name = "Ромашка",
                        address = "улица Московская 33",
                        phoneForUser = "+79008005522",
                        city = "Москва",
                        descriptions = "Лучший ресторан под названием \"Ромашка\"",
                        images = null,
                        idImage = 3,/*listOf(
                            OrganizationImagesProfile(null,
                                "1.jpg"))*/
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
                                        name = "Карбонара",
                                        price = 470.0,
                                        weight = 300f,
                                        description = "Со сливками",
                                        imageProduct = null
                                    ),
                                    Product(
                                        name = "Фетучини",
                                        price = 600.0,
                                        weight = 400f,
                                        description = "Креветки и залень",
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
                        ),
                        user = null
                    ),
                    Organization(
                        name = "Бургер кинг",
                        address = "проспект Ленина 40",
                        phoneForUser = "+79205748652",
                        city = "Санкт-Петербург",
                        idImage = 2,
                        descriptions = "Маркетнг - это не для нас",
                        images = null/*listOf(OrganizationImagesProfile(null,
                                "1.jpg"))*/,
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

                        user = null
                    )
                )

            items[0].user = Test(1, items[0])

            serviceOrganization.insertOrganization(items)
        }
    }
}