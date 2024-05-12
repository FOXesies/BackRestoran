package org.example.entity.Favorite

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import org.example.organization.model.Organization
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class FavoriteOrganization(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idFavorite: Long? = null,
    var idOrganizationFav: Long? = null,

    @OneToOne
    @JoinColumn(name = "idOrganizationFav", referencedColumnName = "idOrganization")
    var organization: Organization? = null
)