package org.example.entity.Users_.DTO

data class SingUpRequest(
    var phone: String,
    var name: String,
    var city: String,
    var password: String,
)