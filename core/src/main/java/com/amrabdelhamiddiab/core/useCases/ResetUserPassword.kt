package com.amrabdelhamiddiab.core.useCases

import com.amrabdelhamiddiab.core.data.RepositoryResetPassword

class ResetUserPassword(private val repositoryResetPassword: RepositoryResetPassword) {
    suspend operator fun invoke(email: String) =
        repositoryResetPassword.resetPasswordByFirebase(email)
}