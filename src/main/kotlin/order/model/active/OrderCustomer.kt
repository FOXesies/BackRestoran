package org.example.order.model.active

import jakarta.persistence.*
import lombok.ToString
import org.example.entity.Users_.Users
import org.example.feedbacks.entity.FeedBacks
import org.example.order.model.AddressUser
import org.example.order.model.StatusOrder
import org.example.organization.model.Organization
import org.example.organization_city.model.LocationOrganization
import java.time.LocalDateTime

@Entity
data class OrderCustomer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderId: Long = 0,

    @OneToOne(cascade = [ CascadeType.MERGE])
    var user: Users? = null,
    @OneToOne(cascade = [CascadeType.MERGE])
    var organization: Organization? = null,

    @OneToOne(cascade = [CascadeType.ALL])
    var addressUser: AddressUser? = null,
    var phoneUser: String? = null,
    var fromTimeDelivery: LocalDateTime?,
    var toTimeDelivery: LocalDateTime?,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    var productOrder: List<ProductInOrder> = mutableListOf(),
    var status: StatusOrder? = null,
    var isSelf: Boolean? = null,
    @OneToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "id_location_id_location", unique = false)
    var idLocation: LocationOrganization? = null,

    @OneToOne(cascade = [CascadeType.MERGE])
    var canceledInfo: CanceledInfo? = null,

    @OneToOne(cascade = [CascadeType.ALL])
    var feedBacks: FeedBacks? = null,

    var summ: Double? = null,
    var comment: String? = null
)
