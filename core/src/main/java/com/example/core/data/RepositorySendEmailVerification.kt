package com.example.core.data

class RepositorySendEmailVerification(private val iSendEmailVerification: ISendEmailVerification) {
    suspend fun sendEmailVerificationByFireBae() =
        iSendEmailVerification.sendEmailVerification()

}