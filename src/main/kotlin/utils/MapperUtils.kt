package org.example.utils

import org.example.DTO.Basket.BasketItemDtom
import org.example.DTO.Basket.ProductInBasket
import org.example.DTO.Category.CategoryDTO
import org.example.entity.Basket.BasketItem
import org.example.entity.Basket.ProductBasket
import org.example.entity.Category.Category
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
import org.example.products.DTO.ProductResponse
import org.example.products.entity.Product
import org.example.products.repository.ProductRepository
import org.example.service.ImageSearchUtils
import org.springframework.stereotype.Service

@Service
class MapperUtils {
    companion object {
        fun mapOrganizationInDTO(organization: Organization): OrganizationDTO {
            return OrganizationDTO(
                organization.idOrganization,
                organization.name,
                organization.phoneForUser,
                organization.locationsAll.associate { it.nameCity to it.locationInCity.map { it.address }},
                organization.idImage,
                organization.descriptions,
                organization.category.map{ mapCategoryDTO(it) },
                if(organization.ratings.isEmpty()) -1.0 else organization.ratings.map { it.rating }.average(),
                organization.ratings.size ,
                organization.images?.map { ImageSearchUtils.getInputStream(it.data) }
            )
        }
        fun mapOrganizationIdInDTO(organization: Organization): OrganizationIdDTO {
            return OrganizationIdDTO(
                organization.idOrganization,
                organization.name,
                organization.phoneForUser,
                organization.idImage,
                organization.descriptions,
                organization.category.map { it.name },
                organization.locationsAll.associate { city ->
                    city.nameCity!! to city.locationInCity
                        .map {
                            CityOrganization(it.address!!, Point(it.lat!!, it.lon!!))
                        } },
                organization.category.associateBy ({ it.name }, {it.product}),
                if(organization.ratings.isNotEmpty()) organization.ratings.map { it.rating }.average() else 0.0,
                organization.ratings.size ,
                false,
                organization.images?.map { ImageSearchUtils.getInputStream(it.data) } //organization.images?.map { ImageSearchUtils.getInputStream(it) }
            )
        }
        fun mapBasketInDTO(basket: BasketItem, productService: ProductRepository): BasketItemDtom {
            val item = basket.productsPick.map { ProductInBasket(productService.findById(it.productId!!).get(), it.count) }.toMutableList()
            return BasketItemDtom(
                idRestoraunt = basket.idRestaurant,
                summ = basket.summ,
                productsPick = item
            )
        }

        fun mapCategoryDTO(category: Category): CategoryDTO {
            return CategoryDTO(
                category.name,
                category.images
            )
        }

        fun mapProductBasketInOrder(productBasket: List<ProductBasket>): List<ProductInOrder> {
            return productBasket.map { ProductInOrder(
                idProduct = it.productId,
                count = it.count) }
        }

        fun mapOrderInComplete(order: OrderCustomer): CompleteOrder {
            return CompleteOrder(
                orderId = order.orderId,
                idDriver = order.idDriver,
                idUser = order.idUser,
                idOrganization = order.idOrganization,
                addressUser = order.addressUser,
                idLocation = order.idLocation,
                phoneUser = order.phoneUser,
                toTimeDelivery = order.toTimeDelivery,
                productOrder = order.productOrder.map {
                    ProductInOrderComplete(
                        it.idProductInOrder,
                        it.idProduct,
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
                idUser = order.idUser,
                idOrganization = order.idOrganization,
                idLocation = order.idLocation,
                phoneUser = order.phoneUser,
                toTimeCooling = order.toTimeCooling,
                productOrder = order.productOrder.map {
                    ProductInOrderComplete(
                        it.idProductInOrder,
                        it.idProduct,
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
                idUser = order.idUser,
                uuid = order.uuid!!.id,
                idOrganization = order.idOrganization,
                idLocation = order.idLocation,
                phoneUser = order.phoneUser,
                toTimeCooling = order.toTimeCooling,
                productOrder = order.productOrder.map {
                    ProductInOrderComplete(
                        it.idProductInOrder,
                        it.idProduct,
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
                idUser = order.idUser,
                uuid = order.uuid!!.id,
                idOrganization = order.idOrganization,
                idLocation = order.idLocation,
                phoneUser = order.phoneUser,
                toTimeDelivery = order.toTimeDelivery,
                productOrder = order.productOrder.map {
                    ProductInOrderComplete(
                        it.idProductInOrder,
                        it.idProduct,
                        it.count
                    )
                },
                summ = order.summ,
                comment = order.comment
            )
        }

        fun mapOrgInBasicInfo(organization: Organization): OrganizationForUpdate {
            return OrganizationForUpdate(
                idorganization = organization.idOrganization,
                idImage = organization.idImage,
                nameOrganization = organization.name,
            )
        }

        fun mapProductInBasicInfo(product: Product): ProductResponse {
            return ProductResponse(
                idProduct = product.idProduct,
                name = product.name,
                idImage = product.imageProduct,
                price = product.price,
                description = product.description,
            )
        }
    }

}