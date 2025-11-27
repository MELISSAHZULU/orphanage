package com.example.orphanage.model

data class AuthenticatedUser(
    val uid: String,
    val username: String,
    val email: String,
    val role: String
)
