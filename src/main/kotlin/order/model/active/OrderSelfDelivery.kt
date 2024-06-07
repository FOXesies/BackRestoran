package org.example.order.model.active

import jakarta.persistence.*
import lombok.ToString
import org.example.entity.Users_.Users
import org.example.order.model.StatusOrder
import org.example.organization.model.Organization
import org.example.organization_city.model.LocationOrganization
import java.time.LocalDateTime

/*
@Entity
data class OrderSelfDelivery (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idOrderSelf: Long? = null,
    @OneToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "user_profileuuid", unique = false)
    var user: Users,
    @OneToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "organization_id_organization", unique = false)
    var organization: Organization? = null,

    @OneToOne(cascade = [CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH])
    var uuid: UUIDCustom? = null,

    var phoneUser: String? = null,
    var fromTimeCooking: LocalDateTime?,
    var toTimeCooking: LocalDateTime?,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var productOrder: List<ProductInOrder> = mutableListOf(),
    var status: StatusOrder? = null,

    var summ: Double? = null,
    var comment: String = ""
)*/
