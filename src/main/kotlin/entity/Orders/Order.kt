package org.example.entity.Orders

import jakarta.persistence.*
import lombok.ToString
import org.example.entity.Organization.Organization

@Entity
data class Order(
    @Id
    val idUser: Long,
    var idOrganization: Long,

    @OneToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    var products: List<ProductInOrder>
)
