package org.example.entity

import jakarta.persistence.*
import lombok.*

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var idCategory: Long? = null,

    @Column()
    private var name: String
) {

}