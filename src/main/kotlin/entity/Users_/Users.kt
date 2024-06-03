package org.example.entity.Users_

import jakarta.persistence.*
import lombok.ToString
import org.example.basket.entity.BasketItem
import org.example.entity.Users.ERole
import org.example.favorite.entity.FavoriteProduct
import java.time.LocalDateTime

@Entity
data class Users(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var profileUUID: Long? = null,
    var city: String? = null,
    var name: String? = null,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var favoriteProducts: MutableList<FavoriteProduct> = mutableListOf(),
    var password: String? = null,
    var dateOfCreate: LocalDateTime = LocalDateTime.now(),
    var phone: String? = null,
    @OneToOne
    var basketItem: BasketItem = BasketItem(),
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "customer_roles",
        joinColumns = [JoinColumn(name = "profileuuid_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id_role")]
    )
    @ToString.Exclude
    var roles: MutableSet<ERole> = mutableSetOf()
)