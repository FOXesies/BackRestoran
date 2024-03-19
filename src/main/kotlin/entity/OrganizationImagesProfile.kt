package org.example.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.Type
import java.io.InputStream

@Entity
data class OrganizationImagesProfile(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idOrganizationImage: Long? = null,/*
    @JsonIgnore
    @Type("org.hibernate.type.MaterializedBlobType")*/

    //Path in image
    val data: String
)