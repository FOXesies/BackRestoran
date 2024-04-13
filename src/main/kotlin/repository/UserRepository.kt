package org.example.repository

import org.example.entity.users.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository: JpaRepository<Users, Long> {
    @Query("SELECT u FROM Users u WHERE u.verificationCode = ?1")
    fun findByVerificationCode(code: String?): Users?

    fun findByEmailIdIgnoreCase(emailId: String?): Users?
}