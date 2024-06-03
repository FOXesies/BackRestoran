package org.example.auth.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
data class Sessions(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var sessionId: String? = null,
    var phone: String? = null,
    var lastAccessTime: LocalDateTime? = LocalDateTime.now(),
    var dateOfCreate: LocalDateTime? = LocalDateTime.now()
)