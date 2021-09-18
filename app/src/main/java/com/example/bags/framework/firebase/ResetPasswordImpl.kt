package com.example.bags.framework.firebase

import android.util.Log
import com.example.bags.MainActivity.Companion.TAG
import com.example.core.data.IResetPassword
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class ResetPasswordImpl(private val mAuth: FirebaseAuth) : IResetPassword {
    override suspend fun resetPassword(email: String): Boolean {
        return try {
            mAuth.sendPasswordResetEmail(email).await()
            Log.d(TAG, "555555555555")
            true
        } catch (ex: Exception) {
            Log.d(TAG,"33333333333333" +ex.message.toString())
            false
        }
    }
}