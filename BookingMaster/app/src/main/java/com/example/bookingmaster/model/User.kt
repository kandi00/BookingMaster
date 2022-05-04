package com.example.bookingmaster.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    var email: String = "",
    var password: String = "",
)


@JsonClass(generateAdapter = true)
data class LoginRequest (
    var email: String,
    var password: String
)

@JsonClass(generateAdapter = true)
data class RegistrationRequest (
    var displayName: String,
    var userName: String,
    var email: String,
    var password: String
)

