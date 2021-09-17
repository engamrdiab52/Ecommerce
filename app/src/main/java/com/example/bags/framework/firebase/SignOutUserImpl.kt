package com.example.bags.framework.firebase

import android.util.Log
import com.example.bags.MainActivity.Companion.TAG
import com.example.core.data.ISignOutUser
import com.google.firebase.auth.FirebaseAuth

class SignOutUserImpl(private val mAuth: FirebaseAuth): ISignOutUser{
    override suspend fun signOutUser() {
        try {
            mAuth.signOut()
            Log.d(TAG, "UserSignedOut...")
        }catch (ex: Exception){
            Log.d(TAG, ex.message.toString())
        }
        
       
    }
}