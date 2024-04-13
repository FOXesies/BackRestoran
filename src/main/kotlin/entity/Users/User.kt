package org.example.entity.users

import jakarta.persistence.*
import org.example.entity.Users.ERole
import kotlin.reflect.jvm.internal.impl.types.checker.TypeRefinementSupport.Enabled

@Entity
@Table(name = "users")
data class Users(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    val id: Long? = null,
    val name: String? = null,
    val phone: String? = null,
    val mail: String? = null,
    val password: String? = null,
    @Enumerated(EnumType.STRING)
    val role: ERole? = null,

    @Column(name = "verification_code", length = 64)
    var verificationCode: String? = null,
    var enabled: Boolean = false
)