package org.example.organization.model

import jakarta.persistence.*

@Entity
data class Test(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @OneToOne
    var organization: Organization? = null
)
