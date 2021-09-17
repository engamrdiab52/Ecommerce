package com.example.core.domain

import java.util.*

data class User(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val email: String,
    val password: String,
    val imageUrl: String,
    val loggedIn: Boolean
)