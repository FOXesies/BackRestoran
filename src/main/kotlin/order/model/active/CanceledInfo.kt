package org.example.order.model.active

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class CanceledInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCancel: Long? = null,
    var commnet: String? = null,
    var timeCancel: LocalDateTime? = null
)