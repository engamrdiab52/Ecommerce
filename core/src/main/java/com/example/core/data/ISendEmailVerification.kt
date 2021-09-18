package com.example.core.data

interface ISendEmailVerification {
    suspend fun sendEmailVerification():Boolean
}