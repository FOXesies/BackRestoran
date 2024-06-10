package org.example.basket.entity

import jakarta.persistence.*
import lombok.ToString
import org.example.entity.Users_.Users
import org.example.organization.model.Organization

@Entity
data class BasketItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idBasket: Long? = null,
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE])
    var organization: Organization? = null,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var productsPick: MutableList<ProductBasket> = mutableListOf(),
    var city: String? = null,
    var summ: Double = 0.0
)
