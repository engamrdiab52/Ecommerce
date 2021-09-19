package com.example.bags.framework.firebase

import android.content.Context
import android.util.Log
import com.example.bags.MainActivity.Companion.TAG
import com.example.bags.framework.utilis.checkInternetConnection
import com.example.core.data.IEmailVerifiedState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeoutOrNull

class EmailVerifiesStateImpl(private val mAuth: FirebaseAuth, private val context: Context) :
    IEmailVerifiedState {
    override suspend fun isEmailVerified(): Boolean {
/*        return try {
            if (mAuth.currentUser != null){
                Log.d(TAG,"EmailVerifiesStateImpl :  " + mAuth.currentUser!!.email.toString())
                mAuth.currentUser!!.isEmailVerified

                true
            }else{
                Log.d(TAG,"EmailVerifiesStateImpl :  " + mAuth.currentUser!!.isEmailVerified.toString())
                false
            }

        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
            false
        }*/
        return if (checkInternetConnection(context)) {
            val result = withTimeoutOrNull(3000L) {
                mAuth.currentUser?.isEmailVerified
            }
            result ?: false
        } else {
            Log.d(TAG, "EmailVerifiesStateImpl : No Network")
            false
        }

    }
}