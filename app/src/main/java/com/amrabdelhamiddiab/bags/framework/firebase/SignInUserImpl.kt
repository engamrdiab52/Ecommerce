package com.amrabdelhamiddiab.bags.framework.firebase

import android.util.Log
import com.amrabdelhamiddiab.bags.MainActivity.Companion.TAG
import com.amrabdelhamiddiab.core.data.ISignInUser
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class SignInUserImpl(private val mAuth: FirebaseAuth) : ISignInUser {

    //will modify return value to return ,and because u r still inside launch u can await it
    // and modify the viewModel livedata value inside the launch in the viewModel
    override suspend fun signInUser(email: String, password: String): Boolean {
        return try {
            val authResult = mAuth.signInWithEmailAndPassword(email, password).await()
            if (authResult != null) {
                Log.d(TAG, mAuth.currentUser?.email.toString())
                true
            } else {
                Log.d(TAG, "user didn't sign in")
                false
            }
        } catch (ex: Exception) {
            Log.d(TAG, ex.message.toString())
            false
        }
    }
}