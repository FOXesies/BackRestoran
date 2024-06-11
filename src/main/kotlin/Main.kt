package org.example

import org.example.auth.repository.ERoleCustomerRepository
import org.example.auth.service.UserService
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
import org.example.entity.Users_.Users
import org.example.products.repository.ProductRepository
import org.example.service.ImageSearchUtils
import org.example.organization.service.ServiceOrganization
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder


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
                          basketRepository: BasketRepository, userRepository: UserService, orderRepository: OrderRepository,
                          roleRepository: ERoleCustomerRepository, passwordEncoder: PasswordEncoder
    ): CommandLineRunner {
        return CommandLineRunner {
            roleRepository.save(ERole(nameRole = "CUSTOMER"))
            roleRepository.save(ERole(nameRole = "ORGANIZATION"))

            imageService.imageImpl.save(
                Image(
                    id = 1,
                    value = Main.javaClass.getResource("/organizations_images/1.jpg").readBytes(), main = true
                )
            )
            imageService.imageImpl.save(
                Image(
                    id = 2,
                    value = Main.javaClass.getResource("/organizations_images/2.png").readBytes(), main = true
                )
            )
            imageService.imageImpl.save(
                Image(
                    id = 3,
                    value = Main.javaClass.getResource("/organizations_images/3.jpg").readBytes(), main = true
                )
            )

            val items = listOf(
                Organization(
                    name = "Ромашка",
                    password = passwordEncoder.encode("sdfs"),
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
                    idImages = mutableListOf(
                        Image(
                            value = Main.javaClass.getResource("/organizations_images/1.jpg").readBytes(), main = true
                        ),
                        Image(
                            value = Main.javaClass.getResource("/organizations_images/3.jpg").readBytes()
                        )
                    ),
                    login = "login1",
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
                            description = "Карбонара – популярное блюдо традиционной кухни Италии. Представляет собой пасту, заправленную особым мясным соусом.\n" +
                                    "Основа блюда – сама отварная «аль-денте» паста (то есть, отваренная не до полной готовности). Пасту могут заменить обычные тонкие спагетти.\n" +
                                    "Соус может быть приготовлен в различных вариантах. Адаптированный соус карбонара под используемые продукты питания в России содержит свиную грудинку или бекон (подкопченные).",
                            category = Category(name = "Паста"),
                            images = mutableListOf(Image(
                                value = Main.javaClass.getResource("/organizations_images/карб.jpeg").readBytes(), main = true
                            ))
                        ),
                        Product(
                            name = "Фетучини",
                            price = 600.0,
                            weight = 400f,
                            description = "Креветки и залень",
                            category = Category(name = "Паста"),
                            images = mutableListOf(Image(
                                value = Main.javaClass.getResource("/organizations_images/ауе.jpg").readBytes(), main = true
                            ))
                        )

                    ),
                ),
                Organization(
                    name = "Итальянская кухня",
                    phoneForUser = "+79123456789",
                    password = passwordEncoder.encode("sdfs"),
                    descriptions = "Ресторан итальянской кухни",
                    locationInCity = mutableListOf(
                        LocationOrganization(
                            city = CityOrganization(nameCity = "Москва"),
                            address = "Тверская улица 5", lat = 55.757722, lon = 37.615619
                        ),
                        LocationOrganization(
                            city = CityOrganization(nameCity = "Санкт-Петербург"),
                            address = "Невский проспект 22", lat = 59.934280, lon = 30.335098
                        )
                    ),
                    idImages = mutableListOf(Image(
                        value = Main.javaClass.getResource("/organizations_images/4.jpg").readBytes(), main = true
                    )),
                    login = "login2",
                    products = mutableListOf(
                        Product(
                            name = "Маргарита",
                            price = 350.0,
                            weight = 450f,
                            description = "Классическая пицца с томатами и сыром",
                            category = Category(name = "Пицца")
                        ),
                        Product(
                            name = "Карбонара",
                            price = 470.0,
                            weight = 350f,
                            description = "Спагетти с беконом и сливочным соусом",
                            category = Category(name = "Паста")
                        ),
                        Product(
                            name = "Фетучини с грибами",
                            price = 600.0,
                            weight = 400f,
                            description = "Паста с грибами в сливочном соусе",
                            category = Category(name = "Паста")
                        ),
                        Product(
                            name = "Пицца Пепперони",
                            price = 450.0,
                            weight = 400f,
                            description = "Пицца с острой колбасой пепперони",
                            category = Category(name = "Пицца")
                        ),
                        Product(
                            name = "Тирамису",
                            price = 300.0,
                            weight = 150f,
                            description = "Итальянский десерт с маскарпоне и кофе",
                            category = Category(name = "Десерт")
                        ),
                        Product(
                            name = "Минестроне",
                            price = 250.0,
                            weight = 300f,
                            description = "Овощной суп с пастой",
                            category = Category(name = "Суп")
                        ),
                        Product(
                            name = "Брускетта с помидорами",
                            price = 200.0,
                            weight = 100f,
                            description = "Гренки с томатами и базиликом",
                            category = Category(name = "Закуска")
                        ),
                        Product(
                            name = "Ризотто с морепродуктами",
                            price = 550.0,
                            weight = 350f,
                            description = "Рис с креветками и мидиями в белом вине",
                            category = Category(name = "Основное блюдо")
                        ),
                        Product(
                            name = "Капрезе",
                            price = 250.0,
                            weight = 200f,
                            description = "Салат с томатами, моцареллой и базиликом",
                            category = Category(name = "Салат")
                        ),
                        Product(
                            name = "Панна Котта",
                            price = 300.0,
                            weight = 150f,
                            description = "Десерт из сливок с ягодным соусом",
                            category = Category(name = "Десерт")
                        )
                    )
                ),
                Organization(
                    password = passwordEncoder.encode("sdfs"),
                    name = "Бургер кинг",
                    phoneForUser = "+79205748652",
                    idImages = mutableListOf(Image(
                        value = Main.javaClass.getResource("/organizations_images/6.jpg").readBytes(), main = true
                    )),
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
                    login = "login3",
                    descriptions = "Маркетнг - это не для нас",
                    products = mutableListOf(
                        Product(
                            name = "Пицца без Кока-колой",
                            price = 100.0,
                            weight = 100f,
                            description = "Сильногазированая",
                            images = mutableListOf(
                                Image(
                                    value = Main.javaClass.getResource("/organizations_images/2.png").readBytes(), main = true
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
                                    value = Main.javaClass.getResource("/organizations_images/2.png").readBytes(), main = true
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
                ),
                Organization(
                    password = passwordEncoder.encode("sdfs"),
                    name = "Франицуская лавка",
                    phoneForUser = "+79205748652",
                    idImages = mutableListOf(Image(
                        value = Main.javaClass.getResource("/organizations_images/5.jpg").readBytes(), main = true
                    )),
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
                    login = "login4",
                    products = mutableListOf(
                        Product(
                            name = "Пицца без Кока-колой",
                            price = 100.0,
                            weight = 100f,
                            description = "Сильногазированая",
                            images = mutableListOf(
                                Image(
                                    value = Main.javaClass.getResource("/organizations_images/2.png").readBytes(), main = true
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
                                    value = Main.javaClass.getResource("/organizations_images/2.png").readBytes(), main = true
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

            userRepository.save(
                Users(
                    profileUUID = 1,
                    city = "Москва",
                    name = "Никитос",
                    password = passwordEncoder.encode("1"),
                    phone = "89308391610"
            )
            )
        }
    }
}