package com.example.core.data

interface IResetPassword {
    suspend fun resetPassword(newPassword: String)
}