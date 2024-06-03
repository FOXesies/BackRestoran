package org.example.auth.repository

import org.example.entity.Users.ERole
import org.springframework.data.jpa.repository.JpaRepository

interface ERoleCustomerRepository: JpaRepository<ERole, Long>{
}