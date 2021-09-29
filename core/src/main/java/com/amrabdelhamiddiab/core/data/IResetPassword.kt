package com.amrabdelhamiddiab.core.data

interface IResetPassword {
    suspend fun resetPassword(email: String):Boolean
}