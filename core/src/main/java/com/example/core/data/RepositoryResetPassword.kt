package com.example.core.data

class RepositoryResetPassword(private val iResetPassword: IResetPassword) {
    suspend fun resetPasswordByFirebase(password: String) =
        iResetPassword.resetPassword(password)
}