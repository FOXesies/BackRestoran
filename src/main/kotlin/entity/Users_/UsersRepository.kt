package entity.Users_;

import org.example.entity.Users_.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UsersRepository : JpaRepository<Users, Long> {
}