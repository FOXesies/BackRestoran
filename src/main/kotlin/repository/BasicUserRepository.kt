package org.example.repository

import org.example.entity.Users_.Users
import org.springframework.data.jpa.repository.JpaRepository

interface BasicUserRepository: JpaRepository<Users, Long>{
    fun findByPhone(phone: String): Users?
}