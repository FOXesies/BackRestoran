package org.example.repository

import org.example.entity.Users_.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface BasicUserRepository: JpaRepository<Customer, Long>{
}