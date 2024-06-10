package org.example.utils

import org.example.DTO.Basket.BasketItemDtom
import org.example.DTO.Basket.ProductInBasket
import org.example.DTO.Category.CategoryDTO
import org.example.admin.auth.model.OrganizationResponse
import org.example.admin.products.controller.ProductDToUpdate
import org.example.basket.entity.BasketItem
import org.example.basket.entity.ProductBasket
import org.example.products_category.entity.Category
import org.example.entity.Users_.Users
import org.example.entity.Users_.DTO.UserResponse
import org.example.feedbacks.entity.FeedBacks
import org.example.feedbacks.entity.FeedbacksDTO
import org.example.order.DTO.sen_response.*
import org.example.order.model.active.OrderCustomer
import org.example.order.model.active.ProductInOrder
import org.example.organization.model.DTO.*
import org.example.organization.model.Organization
import org.example.organization_city.model.DTO.CityOrganization
import org.example.organization_city.model.DTO.Point
import org.example.products.entity.Product
import org.example.products.repository.ProductRepository
import org.springframework.stereotype.Service
import organization.model.DTO.BasicInfoResponse
import organization.model.DTO.ImageDTO
import java.time.format.DateTimeFormatter

@Service
class MapperUtils {
    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

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
                organization.idImages.first { it.main },
                organization.descriptions,
                organization.products.map { mapCategoryDTO(it.category!!) }.toSet(),
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
                organization.products.map { it.category!!.name }.toSet(),
                organization.locationInCity.groupBy(
                    keySelector = { it.city.nameCity!! },   // Получаем название города в качестве ключа
                    valueTransform = { CityOrganization(address = it.address, points = Point(it.lat!!, it.lon!!)) }   // Получаем адрес в качестве значения
                ).mapValues { entry -> entry.value.toMutableList() },
                organization.products.groupBy(
                    keySelector = { it.category!!.name },   // Получаем название города в качестве ключа
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
                productsPick = item,
                city = basket.city
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


        fun mapResponseProductInResponseProduct(product: Product): org.example.products.DTO.ResponseProduct {
            return org.example.products.DTO.ResponseProduct(
                id = product.idProduct!!,
                name = product.name,
                description = product.description,
                price = product.price,
                image = if (product.images.size > 0) product.images.first { it.main } else null
            )
        }

        fun mapOrderToPreview(order: OrderCustomer): SendOrderPreview{
            return SendOrderPreview(
                idOrder = order.orderId,
                organizationId = order.organization!!.idOrganization,
                organizationName = order.organization!!.name,
                addressUser = order.addressUser,
                idLocation = order.idLocation,
                fromTimeCooking = order.fromTimeDelivery!!.format(formatter),
                toTimeCooking = order.toTimeDelivery!!.format(formatter),
                status = order.status,
                summ = order.summ,
                isSelf = order.isSelf
            )
        }

        fun mapCompleteOrderToPreview(order: OrderCustomer): SendCompleteOrderDTO {
            return SendCompleteOrderDTO(
                rating = order.feedBacks?.rating,
                orderPreview = mapOrderToPreview(order),
            )
        }

        fun mapOrderToCancelPreview(order: OrderCustomer): SendCanceledOrderPreview {
            return SendCanceledOrderPreview(
                orderPreview = mapOrderToPreview(order),
                comment = order.canceledInfo!!.commnet,
                timeCandeled = order.canceledInfo!!.timeCancel!!.format(formatter),
                )
        }

        fun mapOrderInSendFull(order: OrderCustomer): SendBasicInfoOrder {
            return SendBasicInfoOrder(
                orderId = order.orderId,
                userName = order.user!!.name,
                addressUser = order.addressUser,
                phoneUser = order.phoneUser,
                idLocation = order.idLocation,
                fromTimeDelivery = order.fromTimeDelivery!!.format(formatter),
                toTimeDelivery = order.toTimeDelivery!!.format(formatter),
                productOrder = order.productOrder.map { mapResponseProductInResponseProduct(it.product!!) },
                counts = order.productOrder.map { it.count!! },
                status = order.status,
                payment = order.paymentType,
                isSelf = order.isSelf,
                canceledTime = order.canceledInfo?.timeCancel?.format(formatter),
                canceledInfo = order.canceledInfo?.commnet,
                timeComment = order.feedBacks?.timeComment?.format(formatter),
                feedBacksRating = order.feedBacks?.rating,
                feedBacksComment = order.feedBacks?.comentRating,
                summ = order.summ,
                comment = order.comment
            )
        }

        fun mapOrderInSendFullUser(order: OrderCustomer): SendBasicInfoOrderUser {
            return SendBasicInfoOrderUser(
                orderId = order.orderId,
                orgName = order.organization!!.name,
                orgPhone = order.organization!!.phoneForUser,
                addressUser = order.addressUser,
                phoneUser = order.phoneUser,
                fromTimeDelivery = order.fromTimeDelivery!!.format(formatter),
                toTimeDelivery = order.toTimeDelivery!!.format(formatter),
                productOrder = order.productOrder.map { mapResponseProductInResponseProduct(it.product!!) },
                counts = order.productOrder.map { it.count!! },
                status = order.status,
                isSelf = order.isSelf,
                payment = order.paymentType,
                idLocation = order.idLocation,
                canceledTime = order.canceledInfo?.timeCancel?.format(formatter),
                canceledInfo = order.canceledInfo?.commnet,
                timeComment = order.feedBacks?.timeComment?.format(formatter),
                feedBacksRating = order.feedBacks?.rating,
                feedBacksComment = order.feedBacks?.comentRating,
                summ = order.summ,
                comment = order.comment
            )
        }

        fun mapOrgToBasicInfo(organization: Organization): BasicInfoResponse {
            return BasicInfoResponse(
                idOrg = organization.idOrganization!!,
                name = organization.name,
                phone = organization.phoneForUser,
                description = organization.descriptions,
                locationAll = organization.locationInCity.groupBy(
                    keySelector = { it.city.nameCity!! },   // Получаем название города в качестве ключа
                    valueTransform = { CityOrganization(address = it.address, points = Point(latitude = it.lat!!, longitude = it.lon!!)) }   // Получаем адрес в качестве значения
                ).mapValues { entry -> entry.value.toMutableList() },
                idImages = organization.idImages.map { ImageDTO(id = it.id, value = it.value, main = it.main) }
            )
        }

        fun mapFeedbackToDTO(feed: FeedBacks): FeedbacksDTO {
            return FeedbacksDTO(
                idFeedback = feed.idFeedback!!,
                userName = feed.user.name!!,
                rating = feed.rating!!,
                comment = feed.comentRating,
                dateCreate = feed.timeComment!!.format(formatter)
            )
        }

        fun mapOrganizationIdInDTOHome(organization: Organization, city: String, middleStar: List<Number?>): OrganizationIdDTO {
            return OrganizationIdDTO(
                organization.idOrganization,
                organization.name,
                organization.phoneForUser,
                organization.idImages,
                organization.descriptions,
                organization.products.map { it.category!!.name }.toSet(),
                organization.locationInCity.filter { it.city.nameCity == city }
                    .groupBy(keySelector = {it.city.nameCity!!}, valueTransform = { CityOrganization(address = it.address!!, points = Point(it.lat!!, it.lon!!)) }),
                organization.products.groupBy(
                    keySelector = { it.category!!.name },   // Получаем название города в качестве ключа
                    valueTransform = { mapResponseProductInResponseProduct(it) }   // Получаем адрес в качестве значения
                ).mapValues { entry -> entry.value.toMutableList() },
                middleStar[0]?.toDouble()?: -1.0,
                middleStar[1]?.toInt()?: 0
                /*if(organization.ratings.isNotEmpty()) organization.ratings.map { it.rating }.average() else 0.0,
                organization.ratings.size */
            )
        }


    }

}