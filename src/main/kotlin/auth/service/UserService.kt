package org.example.auth.service

import org.example.auth.repository.ERoleCustomerRepository
import org.example.entity.Users_.Users
import org.example.repository.BasicUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service


@Service
class UserService(usersRepository: BasicUserRepository) {
    private val usersRepository: BasicUserRepository = usersRepository

    @Autowired
    private lateinit var roleRepository: ERoleCustomerRepository

    fun findById(id: Long?): Users {
        return usersRepository.findById(id).orElse(null)
    }

    fun findByPhone(phone: String): Users? {
        return usersRepository.findByPhone(phone)
    }

    val currentUser: Users
        get() {
            // Получение имени пользователя из контекста Spring Security
            val username = SecurityContextHolder.getContext()
                .authentication
                .name
            return findByPhone(username)!!
        }

    fun save(users: Users): Users {
        val existingRoles = users.roles.map { role ->
            roleRepository.findById(role.idRole!!).orElseThrow { RuntimeException("Role not found") }
        }.toMutableSet()

        users.roles = existingRoles
        return usersRepository.save(users)
    }
}