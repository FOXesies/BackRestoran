package org.example.entity.Basket

import jakarta.persistence.*
import lombok.ToString

@Entity
data class BasketItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idBasket: Long? = null,
    var idUser: Long? = 1,
    var idRestaurant: Long? = null,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var productsPick: List<ProductBasket> = listOf(),
    var summ: Double = 0.0
)
