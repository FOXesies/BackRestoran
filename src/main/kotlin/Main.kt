package org.example

import org.example.auth.repository.ERoleCustomerRepository
import org.example.products_category.entity.Category
import org.example.entity.Image
import org.example.products.entity.Product
import org.example.order.repository.active.OrderRepository
import org.example.organization_city.model.CityOrganization
import org.example.organization_city.model.LocationOrganization
import org.example.organization.model.Organization
import org.example.repository.BasicUserRepository
import org.example.basket.repository.BasketRepository
import org.example.entity.Users.ERole
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
                          basketRepository: BasketRepository, userRepository: BasicUserRepository, orderRepository: OrderRepository, roleRepository: ERoleCustomerRepository
    ): CommandLineRunner {
        return CommandLineRunner {
            roleRepository.save(ERole(nameRole = "CUSTOMER"))
            roleRepository.save(ERole(nameRole = "ORGANIZATION"))

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

            val items = listOf(
                Organization(
                    name = "Ромашка",
                    phoneForUser = "+79008005522",
                    descriptions = "Лучший ресторан под названием \"Ромашка\"",
                    locationInCity = mutableListOf(
                        LocationOrganization(
                            city = CityOrganization(nameCity = "Москва"),
                            address = "Москва улица 12", lat = 59.852081, lon = 30.238487
                        ),
                        LocationOrganization(
                            city = CityOrganization(nameCity = "Москва"),
                            address = "Другая улица 10", lat = 59.652081, lon = 31.238487
                        ),
                        LocationOrganization(
                            city = CityOrganization(nameCity = "Владимир"),
                            address = "улица Узкая 2", lat = 59.852081, lon = 30.238487
                        ),
                        LocationOrganization(
                            city = CityOrganization(nameCity = "Владимир"),
                            address = "улица Длинная 1", lat = 59.652081, lon = 31.238487
                        )
                    ),
                    idImages = mutableListOf(imageService.imageImpl.findById(1).get()),
                    products = mutableListOf(
                        Product(
                            name = "Пицца с Кока-колой",
                            price = 100.0,
                            weight = 100f,
                            description = "Сильногазированая",
                            category = Category(name = "Пицца")
                        ),
                        Product(
                            name = "Пицца По-Царски",
                            price = 450.0,
                            weight = 400f,
                            description = "20см диаметр",
                            category = Category(name = "Пицца")
                        ),
                        Product(
                            name = "Карбонара",
                            price = 470.0,
                            weight = 300f,
                            description = "Со сливками",
                            category = Category(name = "Паста")
                        ),
                        Product(
                            name = "Фетучини",
                            price = 600.0,
                            weight = 400f,
                            description = "Креветки и залень",
                            category = Category(name = "Паста")
                        )

                    ),
                ),
                Organization(
                    name = "Бургер кинг",
                    phoneForUser = "+79205748652",
                    idImages = mutableListOf(imageService.imageImpl.findById(3).get()),
                    locationInCity = mutableListOf(
                        LocationOrganization(
                            city = CityOrganization(nameCity = "Санкт-Петербург"),
                            address = "проспект Ленина 40", lat = 59.852081, lon = 30.238487
                        ),
                        LocationOrganization(
                            city = CityOrganization(nameCity = "Москва"),
                            address = "проспект Проспекта 8", lat = 59.852081, lon = 30.238487
                        )
                    ),
                    descriptions = "Маркетнг - это не для нас",
                    products = mutableListOf(
                        Product(
                            name = "Пицца без Кока-колой",
                            price = 100.0,
                            weight = 100f,
                            description = "Сильногазированая",
                            images = mutableListOf(
                                Image(
                                    value = Main.javaClass.getResource("/organizations_images/2.png").readBytes()
                                )
                            ),
                            category = Category(name = "Пицца")
                        ),
                        Product(
                            name = "Пицца По-Холопски",
                            price = 450.0,
                            weight = 400f,
                            description = "20см диаметр",
                            images = mutableListOf(
                                Image(
                                    value = Main.javaClass.getResource("/organizations_images/2.png").readBytes()
                                )
                            ),
                            category = Category(name = "Пицца")
                        ),
                        Product(
                            name = "Борщ",
                            price = 100.0,
                            weight = 100f,
                            description = "со сметаной",
                            category = Category(name = "Суп")
                        )

                    )
                )
            )

            serviceOrganization.insertOrganization(items)
        }
    }
}