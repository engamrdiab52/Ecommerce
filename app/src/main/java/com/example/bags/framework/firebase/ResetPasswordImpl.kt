package com.example.bags.framework.firebase

import android.util.Log
import com.example.bags.MainActivity.Companion.TAG
import com.example.core.data.IResetPassword
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class ResetPasswordImpl(private val mAuth: FirebaseAuth) : IResetPassword {
    override suspend fun resetPassword(email: String) {
        try {
            val authResult = mAuth.sendPasswordResetEmail(email).await()
            Log.d(TAG, authResult.toString())
        } catch (ex: Exception) {
            Log.d(TAG, ex.message.toString())
        }
    }
}