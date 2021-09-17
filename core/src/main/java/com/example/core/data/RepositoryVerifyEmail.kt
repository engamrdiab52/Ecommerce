package com.example.core.data

class RepositoryVerifyEmail(private val iVerifyUserEmail: IVerifyUserEmail) {
    suspend fun verifyUserEmail()= iVerifyUserEmail.verifyUserEmail()
}