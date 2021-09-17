package com.example.core.useCases

import com.example.core.data.RepositoryResetPassword

class ResetUserPassword(private val repositoryResetPassword: RepositoryResetPassword) {
    suspend operator fun invoke(email: String) =
        repositoryResetPassword.resetPasswordByFirebase(email)
}