package com.example.bags.framework.firebase

import android.util.Log
import com.example.bags.MainActivity.Companion.TAG
import com.example.core.data.IEmailVerifiedState
import com.google.firebase.auth.FirebaseAuth

class EmailVerifiesStateImpl(private val mAuth: FirebaseAuth) : IEmailVerifiedState {
    override suspend fun isEmailVerified(): Boolean {
        return try {
            if (mAuth.currentUser != null){
                Log.d(TAG,"EmailVerifiesStateImpl :  " + mAuth.currentUser!!.email.toString())
                mAuth.currentUser!!.isEmailVerified
            }else{
                Log.d(TAG,"EmailVerifiesStateImpl :  " + mAuth.currentUser!!.isEmailVerified.toString())
                false
            }

        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
            false
        }
    }
}