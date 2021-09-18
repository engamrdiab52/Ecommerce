package com.example.core.data

class RepositoryEmailVerifiedState(private val iEmailVerifiedState: IEmailVerifiedState) {
    suspend fun isEmailVerified()= iEmailVerifiedState.isEmailVerified()
}