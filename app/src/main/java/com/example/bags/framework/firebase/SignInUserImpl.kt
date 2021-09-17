package com.example.bags.framework.firebase

import android.util.Log
import com.example.bags.MainActivity.Companion.TAG
import com.example.core.data.ISignInUser
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class SignInUserImpl(private val mAuth: FirebaseAuth) : ISignInUser {
    override suspend fun signInUser(email: String, password: String) {
        try {
            val authResult = mAuth.signInWithEmailAndPassword(email, password).await()
            Log.d(TAG, authResult.toString())
        } catch (ex: Exception) {
            Log.d(TAG, ex.message.toString())
        }
    }
}