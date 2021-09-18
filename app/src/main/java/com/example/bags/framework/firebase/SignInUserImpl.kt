package com.example.bags.framework.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bags.MainActivity.Companion.TAG
import com.example.core.data.ISignInUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class SignInUserImpl(private val mAuth: FirebaseAuth) : ISignInUser {
    private val _firebaseUser = MutableLiveData<FirebaseUser?>()
    val firebaseUser: LiveData<FirebaseUser?> get() = _firebaseUser

    //will modify return value to return ,and becauase u r still inside launch u can await it
    // and modify the viewmodel livedata value inside the launch in the viewmodel
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