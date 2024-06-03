package org.example.auth.service

import org.example.entity.Users_.Users
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserDetailsServiceIml(val userService: UserService) : UserDetailsService {
/*
    override fun loadUserByPhone(phone: String): UserDetails? {
        return generateUserDetails(userService.findByPhone(phone))
    }
*/

    override fun loadUserByUsername(username: String?): UserDetails {
        return generateUserDetails(userService.findByPhone(username!!)!!)
    }

    fun generateUserDetails(users: org.example.entity.Users_.Users): UserDetails {
        return org.springframework.security.core.userdetails.User(
            users.phone,
            users.password,
            users.roles
                .stream()
                .map { x -> SimpleGrantedAuthority(x.nameRole) }
                .toList()
        )
    }
}