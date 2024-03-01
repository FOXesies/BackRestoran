package org.example.entity

import jakarta.persistence.*
import lombok.*

@Entity
@Getter
@Setter
@Table(name = "organizations")
@AllArgsConstructor
@NoArgsConstructor
data class Organization(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var idOrganization: Long? = null,
    @Column(unique = true, nullable = false)
    private var name: String,
    @Column(nullable = false)
    private var address: String,
    @Column(nullable = false)
    private var phoneForUser: String,
    @Column(nullable = false)
    private var city: String,
    @Column(nullable = true)
    private var descriptions: String?,// жесть
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude // жесть
    private var product: List<Product>,
    @Column(columnDefinition = "bytea")
    private var imageOrganization: ByteArray?
)