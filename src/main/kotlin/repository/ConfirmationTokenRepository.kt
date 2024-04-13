package org.example.repository

import org.example.entity.Users.ConfirmationToken
import org.springframework.data.jpa.repository.JpaRepository

interface ConfirmationTokenRepository: JpaRepository<ConfirmationToken, Long> {

    fun findByEmailIdIgnoreCase(confirmationToken: String): ConfirmationToken?
}