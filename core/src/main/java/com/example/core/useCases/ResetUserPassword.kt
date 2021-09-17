package com.example.core.useCases

import com.example.core.data.RepositoryResetPassword

class ResetUserPassword(private val repositoryResetPassword: RepositoryResetPassword) {
    suspend operator fun invoke(password: String) =
        repositoryResetPassword.resetPasswordByFirebase(password)
}