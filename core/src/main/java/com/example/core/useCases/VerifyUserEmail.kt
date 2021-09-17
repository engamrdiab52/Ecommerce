package com.example.core.useCases

import com.example.core.data.RepositoryVerifyEmail

class VerifyUserEmail(private val repositoryVerifyEmail: RepositoryVerifyEmail) {
    suspend operator fun invoke() = repositoryVerifyEmail.verifyUserEmail()
}