package com.amrabdelhamiddiab.bags.framework.firebase

import android.util.Log
import com.amrabdelhamiddiab.bags.MainActivity.Companion.TAG
import com.amrabdelhamiddiab.core.data.ISignupUser
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class SignUpUserImpl(private val mAuth: FirebaseAuth) : ISignupUser {
    private var isUserCreated: Boolean = false
  //  private var isEmailVerificationSent = false
    override suspend fun signupUser(email: String, password: String):Boolean {
     return   try {
            val authResult = mAuth.createUserWithEmailAndPassword(email, password).await()
            isUserCreated = authResult.user != null
        //    mAuth.currentUser?.sendEmailVerification()?.await()
        //    isEmailVerificationSent = true
            Log.d(TAG, authResult.toString())
            Log.d(TAG, "SignUpUserImpl  : email sent")
         isUserCreated
        } catch (ex: Exception) {
            Log.d(TAG, ex.message.toString())
            isUserCreated = false
        //    isEmailVerificationSent = false
            isUserCreated
        }
    }

   /* override suspend fun userLoggedIn(): Boolean {
        return isUserCreated
    }*/
}