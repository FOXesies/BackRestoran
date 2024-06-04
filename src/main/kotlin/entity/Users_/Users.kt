package org.example.entity.Users_

import jakarta.persistence.*
import lombok.ToString
import org.example.basket.entity.BasketItem
import org.example.entity.Users.ERole
import org.example.favorite.entity.FavoriteOrganization
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
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var favoriteOrganization: MutableList<FavoriteOrganization> = mutableListOf(),
    var password: String? = null,
    var dateOfCreate: LocalDateTime = LocalDateTime.now(),
    var phone: String? = null,
    @OneToOne
    var basketItem: BasketItem = BasketItem(),
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "profileuuid_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id_role")]
    )
    @ToString.Exclude
    var roles: MutableSet<ERole> = mutableSetOf()
)