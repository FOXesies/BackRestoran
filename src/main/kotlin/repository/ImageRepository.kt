package org.example.repository

import org.example.entity.Image
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface ImageRepository : JpaRepository<Image?, Long?> {

}