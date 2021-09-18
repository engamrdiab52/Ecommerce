package com.example.bags.framework.firebase

import android.util.Log
import com.example.bags.MainActivity
import com.example.bags.MainActivity.Companion.TAG
import com.example.core.data.ISendEmailVerification
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class SendEmailVerificationImpl(private val mAuth: FirebaseAuth): ISendEmailVerification {
    override suspend fun sendEmailVerification():Boolean {
      return  try {
            val user = mAuth.currentUser
            if (user != null) {
            user.sendEmailVerification().await()
                Log.d(TAG, "SendEmailVerificationImpl :  Email sent")
                true
            } else {
                Log.d(TAG, "SendEmailVerificationImpl  : NO users signed in")
                false
            }
        } catch (ex: Exception) {
            Log.d(TAG, ex.message.toString())
          false
        }
    }

}