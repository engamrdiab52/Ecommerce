package com.example.core.data

interface ISignupUser {
    suspend fun signupUser(email: String, password: String)
}