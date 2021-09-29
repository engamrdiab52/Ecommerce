package com.amrabdelhamiddiab.core.data

class RepositoryVerifyEmail(private val iVerifyUserEmail: IVerifyUserEmail) {
    suspend fun verifyUserEmail()= iVerifyUserEmail.verifyUserEmail()
}