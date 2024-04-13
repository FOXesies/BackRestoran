package org.example.entity.Users

import org.example.entity.users.Users
import java.util.*
import javax.persistence.*

class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_id")
    private val tokenid: Long = 0

    @Column(name = "confirmation_token")
    private val confirmationToken: String? = null

    @Temporal(TemporalType.TIMESTAMP)
    private val createdDate: Date? = null

    @OneToOne(targetEntity = Users::class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private val user: Users? = null
}