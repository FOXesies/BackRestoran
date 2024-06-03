package org.example.favorite.entity

import jakarta.persistence.*
import org.example.organization.model.Organization

@Entity
data class FavoriteOrganization (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idFavorite: Long? = null,
    @OneToOne
    val organization: Organization,
)