package org.example

import org.example.entity.Category
import org.example.entity.Organization
import org.example.entity.Product
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
    fun commandLineRunner(serviceOrganization: ServiceOrganization): CommandLineRunner {
        return CommandLineRunner {
            serviceOrganization.insertOrganization(
                Organization(
                    name = "Ромашка",
                    address = "улица Московская 33",
                    phoneForUser = "+79008005522",
                    city = "Москва",
                    descriptions = "Лучший ресторан под названием \"Ромашка\"",
                    imageOrganization = null,
                    product = listOf(
                        Product(
                            name = "Кока-кола",
                            price = 100.0,
                            weight = 100f,
                            description = "Сильногазированая",
                            category = Category(null, "Напитки"),
                            imageProduct = null
                        ),
                        Product(
                            name = "Пицца По-Царски",
                            price = 450.0,
                            weight = 400f,
                            description = "20см диаметр",
                            category = Category(null, "Пицца"),
                            imageProduct = null
                        )
                    )
                )
            )
        }
    }
}