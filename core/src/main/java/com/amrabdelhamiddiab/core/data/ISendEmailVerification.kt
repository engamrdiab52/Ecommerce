package com.amrabdelhamiddiab.core.data

interface ISendEmailVerification {
    suspend fun sendEmailVerification():Boolean
}