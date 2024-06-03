package org.example.order.model.active

import jakarta.persistence.*
import lombok.ToString
import org.example.entity.Users_.Users
import org.example.order.model.AddressUser
import org.example.order.model.StatusOrder
import org.example.organization.model.Organization
import org.example.uuid.model.UUIDCustom

@Entity
data class OrderCustomer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderId: Long = 0,

    var idDriver: Long? = null,
    @OneToOne
    var user: Users,
    @OneToOne
    var organization: Organization,

    @OneToOne(cascade = [CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH])
    var uuid: UUIDCustom? = null,

    @OneToOne(cascade = [CascadeType.ALL])
    var addressUser: AddressUser? = null,
    var idLocation: Long? = null,
    var phoneUser: String? = null,
    var toTimeDelivery: String? = "now",
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var productOrder: List<ProductInOrder> = mutableListOf(),
    var status: StatusOrder? = null,

    var podezd: String? = null,
    var homephome: String? = null,
    var appartamnet: String? = null,
    var level: String? = null,

    var summ: Double? = null,
    var comment: String? = null
)