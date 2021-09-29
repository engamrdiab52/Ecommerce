package com.amrabdelhamiddiab.bags.framework.firebase

import android.util.Log
import com.amrabdelhamiddiab.bags.MainActivity.Companion.TAG
import com.amrabdelhamiddiab.core.data.ISignOutUser
import com.google.firebase.auth.FirebaseAuth

class SignOutUserImpl(private val mAuth: FirebaseAuth): ISignOutUser{
    override suspend fun signOutUser() {
        try {
            mAuth.signOut()
            Log.d(TAG, "UserSignedOut...${mAuth.currentUser}")
        }catch (ex: Exception){
            Log.d(TAG, ex.message.toString())
        }
        
       
    }
}