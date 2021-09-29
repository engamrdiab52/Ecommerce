package com.amrabdelhamiddiab.core.data

interface IEmailVerifiedState {
    suspend fun isEmailVerified(): Boolean
}