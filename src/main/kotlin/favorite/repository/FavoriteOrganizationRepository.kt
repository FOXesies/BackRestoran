package org.example.favorite.repository;

import org.example.favorite.entity.FavoriteOrganization
import org.springframework.data.jpa.repository.JpaRepository

interface FavoriteOrganizationRepository : JpaRepository<FavoriteOrganization, Long> {
}