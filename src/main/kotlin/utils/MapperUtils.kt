package org.example.utils

import org.example.DTO.Basket.BasketItemDtom
import org.example.DTO.Basket.ProductInBasket
import org.example.DTO.Category.CategoryDTO
import org.example.basket.entity.BasketItem
import org.example.basket.entity.ProductBasket
import org.example.products_category.entity.Category
import org.example.entity.Users_.Users
import org.example.entity.Users_.DTO.UserResponse
import org.example.order.DTO.ActiveOrderDTO
import org.example.order.model.active.OrderCustomer
import org.example.order.model.active.OrderSelfDelivery
import org.example.order.model.active.ProductInOrder
import org.example.order.model.canceled.CanceledOrder
import org.example.order.model.canceled.CanceledOrderSelf
import org.example.order.model.completed.CompleteOrder
import org.example.order.model.completed.CompleteOrderSelf
import org.example.order.model.completed.ProductInOrderComplete
import org.example.organization.model.DTO.*
import org.example.organization.model.Organization
import org.example.organization_city.model.DTO.CityOrganization
import org.example.organization_city.model.DTO.Point
import org.example.products.entity.Product
import org.example.products.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MapperUtils {
    companion object {

        fun mapUserToDTO(customer: Users): UserResponse {
            return UserResponse(
                profileUUID = customer.profileUUID!!,
                city = customer.city!!,
                name = customer.name!!,
                favoriteProducts = customer.favoriteProducts,
                dateOfCreate = customer.dateOfCreate,
                phone = customer.phone!!,
                roles = customer.roles
            )
        }
        fun mapOrganizationInDTO(organization: Organization, middleStar: List<Number?>): OrganizationDTO {
            return OrganizationDTO(
                organization.idOrganization,
                organization.name,
                organization.phoneForUser,
                organization.locationInCity.groupBy(
                    keySelector = { it.city.nameCity },   // Получаем название города в качестве ключа
                    valueTransform = { it.address }   // Получаем адрес в качестве значения
                ).mapValues { entry -> entry.value.toMutableList() },
                organization.idImages,
                organization.descriptions,
                organization.products.map { mapCategoryDTO(it.category) }.toSet(),
                middleStar[0]?.toDouble()?: -1.0,
                middleStar[1]?.toInt()?: 0
            )
        }

        fun mapOrganizationIdInDTO(organization: Organization, middleStar: List<Number?>): OrganizationIdDTO {
            return OrganizationIdDTO(
                organization.idOrganization,
                organization.name,
                organization.phoneForUser,
                organization.idImages,
                organization.descriptions,
                organization.products.map { it.category.name }.toSet(),
                organization.locationInCity.groupBy(
                    keySelector = { it.city.nameCity!! },   // Получаем название города в качестве ключа
                    valueTransform = { CityOrganization(address = it.address, points = Point(it.lat!!, it.lon!!)) }   // Получаем адрес в качестве значения
                ).mapValues { entry -> entry.value.toMutableList() },
                organization.products.groupBy(
                    keySelector = { it.category.name },   // Получаем название города в качестве ключа
                    valueTransform = { mapResponseProductInResponseProduct(it) }   // Получаем адрес в качестве значения
                ).mapValues { entry -> entry.value.toMutableList() },
                middleStar[0]?.toDouble()?: -1.0,
                middleStar[1]?.toInt()?: 0
                /*if(organization.ratings.isNotEmpty()) organization.ratings.map { it.rating }.average() else 0.0,
                organization.ratings.size */
            )
        }
        fun mapBasketInDTO(basket: BasketItem, productService: ProductRepository): BasketItemDtom {
            val item = basket.productsPick.map { ProductInBasket(productService.findById(it.product.idProduct!!).get().idProduct, it.count) }.toMutableList()
            return BasketItemDtom(
                idRestoraunt = basket.organization?.idOrganization,
                summ = basket.summ,
                productsPick = item
            )
        }

        fun mapCategoryDTO(category: Category): CategoryDTO {
            return CategoryDTO(
                category.name,
            )
        }

        fun mapProductBasketInOrder(productBasket: List<ProductBasket>): List<ProductInOrder> {
            return productBasket.map { ProductInOrder(
                product = it.product,
                count = it.count) }
        }

        fun mapOrderInComplete(order: OrderCustomer): CompleteOrder {
            return CompleteOrder(
                orderId = order.orderId,
                user = order.user!!,
                organization = order.organization!!,
                addressUser = order.addressUser,
                phoneUser = order.phoneUser,
                fromTimeDelivery = order.fromTimeDelivery.toString(),
                toTimeDelivery = order.toTimeDelivery.toString(),
                productOrder = order.productOrder.map {
                    ProductInOrderComplete(
                        it.idProductInOrder,
                        it.product,
                        it.count
                    )
                },
                podezd = order.podezd,
                homephome = order.homephome,
                appartamnet = order.appartamnet,
                level = order.level,
                summ = order.summ,
                comment = order.comment
            )
        }

        fun mapOrderInCompleteSelf(order: OrderSelfDelivery): CompleteOrderSelf {
            return CompleteOrderSelf(
                idOrderSelf = order.idOrderSelf,
                user = order.user,
                organization = order.organization,
                phoneUser = order.phoneUser,
                fromTimeCooking = order.fromTimeCooking!!,
                toTimeCooking = order.toTimeCooking,
                productOrder = order.productOrder.map {
                    ProductInOrderComplete(
                        it.idProductInOrder,
                        it.product,
                        it.count
                    )
                },
                summ = order.summ,
                comment = order.comment
            )
        }

        fun mapOrderSelfInCanceled(order: OrderSelfDelivery, comment: String): CanceledOrderSelf {
            return CanceledOrderSelf(
                idOrderSelf = order.idOrderSelf,
                canceled_comment = comment,
                user = order.user,
                organization = order.organization,
                uuid = order.uuid!!.id,
                phoneUser = order.phoneUser,
                fromTimeCooking =order.fromTimeCooking,
                toTimeCooking = order.toTimeCooking,
                productOrder = order.productOrder.map {
                    ProductInOrderComplete(
                        it.idProductInOrder,
                        it.product,
                        it.count
                    )
                },
                summ = order.summ,
                comment = order.comment
            )
        }

        fun mapOrderInCanceled(order: OrderCustomer, comment: String?): CanceledOrder {
            return CanceledOrder(
                orderId = order.orderId,
                canceled_comment = comment,
                user = order.user!!,
                organization = order.organization!!,
                uuid = order.uuid!!.id,
                addressUser = order.addressUser,
                fromTimeCooking = order.fromTimeDelivery.toString(),
                phoneUser = order.phoneUser,
                toTimeDelivery = order.toTimeDelivery.toString(),
                productOrder = order.productOrder.map {
                    ProductInOrderComplete(
                        it.idProductInOrder,
                        it.product,
                        it.count
                    )
                },
                summ = order.summ,
                comment = order.comment
            )
        }

        fun mapResponseProductInProduct(product: org.example.products.entity.ResponseProduct, category: Category): Product {
            return Product(
                name = product.product.name,
                description = product.product.description,
                price = product.product.price,
                weight = product.product.weight,
                category = category,
                images = product.image?.map { org.example.entity.Image(value = it) }?.toMutableList()?: mutableListOf()
            )
        }
        fun mapResponseProductInResponseProduct(product: Product): org.example.products.DTO.ResponseProduct {
            return org.example.products.DTO.ResponseProduct(
                id = product.idProduct!!,
                name = product.name,
                description = product.description,
                price = product.price,
                image = if (product.images.size > 0) product.images[0] else null
            )
        }
    }

}