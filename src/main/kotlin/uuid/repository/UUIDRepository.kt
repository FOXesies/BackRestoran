package org.example.uuid.repository

import org.example.uuid.model.UUIDCustom
import org.springframework.data.jpa.repository.JpaRepository

interface UUIDRepository: JpaRepository<UUIDCustom, Long> {
}