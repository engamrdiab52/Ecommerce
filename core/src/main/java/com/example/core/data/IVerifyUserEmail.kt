package com.example.core.data

interface IVerifyUserEmail {
    suspend fun verifyUserEmail(email: String)
}