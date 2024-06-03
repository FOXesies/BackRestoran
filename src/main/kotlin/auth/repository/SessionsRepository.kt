package org.example.auth.repository

import org.example.auth.entity.Sessions
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface SessionsRepository : JpaRepository<Sessions?, Long?> {
    fun findBySessionId(sessionId: String?): Optional<Sessions?>?

    fun findByPhone(phone: String?): Optional<Sessions?>?
}