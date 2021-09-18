package com.example.core.data

import com.example.core.domain.User

interface ISignInUser {
    suspend fun signInUser(email: String, password: String):Boolean
}