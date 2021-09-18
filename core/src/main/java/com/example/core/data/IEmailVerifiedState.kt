package com.example.core.data

interface IEmailVerifiedState {
    suspend fun isEmailVerified(): Boolean
}