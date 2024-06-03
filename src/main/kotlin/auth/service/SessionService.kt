package org.example.auth.service

import org.example.auth.entity.Sessions
import org.example.auth.repository.SessionsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class SessionService {

    @Autowired
    private lateinit var sessionRepository: SessionsRepository

    fun findById(id: Long): Sessions {
        return sessionRepository.findById(id).orElse(null)!!
    }

    fun findBySessionsId(id: String?): Sessions? {
        return Objects.requireNonNull(sessionRepository.findBySessionId(id))?.orElse(null)
    }

    fun findByPhone(phone: String?): Sessions? {
        return Objects.requireNonNull(sessionRepository.findByPhone(phone))?.orElse(null)
    }

    fun save(sessions: Sessions): Sessions {
        return sessionRepository.save(sessions)
    }
}