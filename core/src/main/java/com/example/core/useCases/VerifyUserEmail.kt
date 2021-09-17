package com.example.core.useCases

import com.example.core.data.RepositoryVerifyEmail

class VerifyUserEmail(private val repositoryVerifyEmail: RepositoryVerifyEmail) {
    suspend operator fun invoke(email: String) = repositoryVerifyEmail.verifyUserEmail(email)
}