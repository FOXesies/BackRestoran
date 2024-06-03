package org.example

import org.example.entity.Basket.BasketItem
import org.example.entity.Category.Category
import org.example.entity.Image
import org.example.products.entity.Product
import org.example.entity.Users_.Customer
import org.example.order.model.active.OrderCustomer
import org.example.order.repository.active.OrderRepository
import org.example.organization.model.CityOrganization
import org.example.organization.model.LocationOrganization
import org.example.organization.model.Organization
import org.example.organization.model.Rating
import org.example.repository.BasicUserRepository
import org.example.repository.BasketRepository
import org.example.products.repository.ProductRepository
import org.example.service.ImageSearchUtils
import org.example.organization.service.ServiceOrganization
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
    fun commandLineRunner(serviceOrganization: ServiceOrganization, imageService: ImageSearchUtils, productRepository: ProductRepository,
                          basketRepository: BasketRepository, userRepository: BasicUserRepository, orderRepository: OrderRepository
    ): CommandLineRunner {
        return CommandLineRunner {

            imageService.imageImpl.save(
                Image(
                    id = 1,
                    value = Main.javaClass.getResource("/organizations_images/1.jpg").readBytes()
                )
            )
            imageService.imageImpl.save(
                Image(
                    id = 2,
                    value = Main.javaClass.getResource("/organizations_images/2.png").readBytes()
                )
            )
            imageService.imageImpl.save(
                Image(
                    id = 3,
                    value = Main.javaClass.getResource("/organizations_images/3.jpg").readBytes()
                )
            )

            userRepository.save(Customer(name = "Никитос"))

            val items = listOf(
                Organization(
                    name = "Ромашка",
                    phoneForUser = "+79008005522",
                    descriptions = "Лучший ресторан под названием \"Ромашка\"",
                    locationsAll = mutableListOf(
                        CityOrganization(
                            nameCity = "Москва",
                            locationInCity = mutableListOf(
                                LocationOrganization(
                                    address = "Москва улица 12", lat = 59.852081, lon = 30.238487
                                ),
                                LocationOrganization(
                                    address = "Другая улица 10", lat = 59.652081, lon = 31.238487
                                )
                            )
                        ),
                        CityOrganization(
                            nameCity = "Москва",
                            locationInCity = mutableListOf(
                                LocationOrganization(
                                    address = "Москва улица 12", lat = 59.852081, lon = 30.238487
                                ),
                                LocationOrganization(
                                    address = "Другая улица 10", lat = 59.652081, lon = 31.238487
                                )
                            )
                        ),
                        CityOrganization(
                            nameCity = "Владимир",
                            locationInCity = mutableListOf(
                                LocationOrganization(
                                    address = "улица Узкая 2", lat = 59.852081, lon = 30.238487
                                ),
                                LocationOrganization(
                                    address = "улица Длинная 1", lat = 59.652081, lon = 31.238487
                                )
                            )
                        )
                    ),
                    idImages = mutableListOf(imageService.imageImpl.findById(1).get()),
                    category = mutableListOf(
                        Category(
                            null, name = "Пицца", product = mutableListOf(
                                Product(
                                    name = "Пицца с Кока-колой",
                                    price = 100.0,
                                    weight = 100f,
                                    description = "Сильногазированая",
                                ),
                                Product(
                                    name = "Пицца По-Царски",
                                    price = 450.0,
                                    weight = 400f,
                                    description = "20см диаметр",
                                )
                            )
                        ),
                        Category(
                            null, name = "Паста", product = mutableListOf(
                                Product(
                                    name = "Карбонара",
                                    price = 470.0,
                                    weight = 300f,
                                    description = "Со сливками",
                                ),
                                Product(
                                    name = "Фетучини",
                                    price = 600.0,
                                    weight = 400f,
                                    description = "Креветки и залень",
                                )
                            )
                        )
                    ),
                    ratings = mutableListOf(
                        Rating(rating = 4),
                        Rating(rating = 2),
                        Rating(rating = 5),
                        Rating(rating = 4),
                    )
                ),
                Organization(
                    name = "Бургер кинг",
                    phoneForUser = "+79205748652",
                    idImages = mutableListOf(imageService.imageImpl.findById(3).get()),
                    locationsAll = mutableListOf(
                        CityOrganization(
                            nameCity = "Санкт-Петербург",
                            locationInCity = mutableListOf(
                                LocationOrganization(
                                    address = "проспект Ленина 40",
                                    lat = 59.852081,
                                    lon = 30.238487
                                )
                            )
                        ),
                        CityOrganization(
                            nameCity = "Москва",
                            locationInCity = mutableListOf(
                                LocationOrganization(
                                    address = "проспект Проспекта 8",
                                    lat = 59.852081,
                                    lon = 30.238487
                                )
                            )
                        )
                    ),
                    descriptions = "Маркетнг - это не для нас",
                    category = mutableListOf(
                        Category(
                            null, name = "Пицца", product = mutableListOf(
                                Product(
                                    name = "Пицца без Кока-колой",
                                    price = 100.0,
                                    weight = 100f,
                                    description = "Сильногазированая",
                                    images = mutableListOf(Image(value = Main.javaClass.getResource("/organizations_images/2.png").readBytes()))
                                ),
                                Product(
                                    name = "Пицца По-Холопски",
                                    price = 450.0,
                                    weight = 400f,
                                    description = "20см диаметр",
                                    images = mutableListOf(Image(value = Main.javaClass.getResource("/organizations_images/2.png").readBytes()))
                                )
                            )
                        ),
                        Category(
                            null, name = "Супы", product = mutableListOf(
                                Product(
                                    name = "Борщ",
                                    price = 100.0,
                                    weight = 100f,
                                    description = "со сметаной",
                                )
                            )
                        )
                    )
                )
            )

            basketRepository.save(BasketItem())

            serviceOrganization.insertOrganization(items)
        }
    }
}