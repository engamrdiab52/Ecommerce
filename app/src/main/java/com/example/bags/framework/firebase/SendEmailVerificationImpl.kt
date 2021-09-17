package com.example.bags.framework.firebase

import android.util.Log
import com.example.bags.MainActivity
import com.example.bags.MainActivity.Companion.TAG
import com.example.core.data.ISendEmailVerification
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class SendEmailVerificationImpl(private val mAuth: FirebaseAuth): ISendEmailVerification {
    override suspend fun sendEmailVerification() {
        try {
            val user = mAuth.currentUser
            if (user != null) {
            user.sendEmailVerification().await()
                Log.d(TAG, "Email sent")
            } else {
                Log.d(TAG, "NO users signed in")
            }
        } catch (ex: Exception) {
            Log.d(TAG, ex.message.toString())
        }
    }

}