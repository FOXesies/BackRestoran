package org.example.feedbacks.repository;

import org.example.entity.Users_.Users
import org.example.feedbacks.entity.FeedBacks
import org.example.organization.model.Organization
import org.example.uuid.model.UUIDCustom
import org.springframework.data.jpa.repository.JpaRepository

interface FeedBacksRepository: JpaRepository<FeedBacks, Long> {
    // Метод для поиска всех отзывов по клиенту (uuidCustom)
    fun findByUuidCustom(uuidCustom: UUIDCustom): FeedBacks

    // Метод для поиска всех отзывов по организации
    fun findByOrganization(organization: Organization): List<FeedBacks>

    // Метод для поиска всех отзывов по организации
    fun findByUser(user: Users): List<FeedBacks>
}