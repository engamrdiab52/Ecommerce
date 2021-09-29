package com.amrabdelhamiddiab.core.useCases

import com.amrabdelhamiddiab.core.data.RepositoryVerifyEmail

class VerifyUserEmail(private val repositoryVerifyEmail: RepositoryVerifyEmail) {
    suspend operator fun invoke() = repositoryVerifyEmail.verifyUserEmail()
}