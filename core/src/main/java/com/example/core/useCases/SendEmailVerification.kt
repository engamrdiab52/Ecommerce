package com.example.core.useCases

import com.example.core.data.RepositorySendEmailVerification

class SendEmailVerification(private val repositorySendEmailVerification: RepositorySendEmailVerification) {
    suspend operator fun invoke() = repositorySendEmailVerification.sendEmailVerificationByFireBae()
}