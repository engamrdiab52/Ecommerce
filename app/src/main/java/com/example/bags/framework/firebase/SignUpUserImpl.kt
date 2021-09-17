package com.example.bags.framework.firebase

import android.util.Log
import com.example.bags.MainActivity.Companion.TAG
import com.example.core.data.ISignupUser
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class SignUpUserImpl(private val mAuth: FirebaseAuth) : ISignupUser {
    override suspend fun signupUser(email: String, password: String) {
        try {
            val authResult = mAuth.createUserWithEmailAndPassword(email, password).await()
            Log.d(TAG, authResult.toString())
        } catch (ex: Exception) {
            Log.d(TAG, ex.message.toString())
        }
    }
}