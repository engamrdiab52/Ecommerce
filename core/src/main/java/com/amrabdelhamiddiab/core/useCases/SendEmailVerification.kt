package com.amrabdelhamiddiab.core.useCases

import com.amrabdelhamiddiab.core.data.RepositorySendEmailVerification

class SendEmailVerification(private val repositorySendEmailVerification: RepositorySendEmailVerification) {
    suspend operator fun invoke() = repositorySendEmailVerification.sendEmailVerificationByFireBae()
}