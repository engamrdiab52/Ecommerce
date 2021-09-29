package com.amrabdelhamiddiab.bags.framework.firebase

import android.content.Context
import android.util.Log
import com.amrabdelhamiddiab.bags.MainActivity.Companion.TAG
import com.amrabdelhamiddiab.bags.framework.utilis.checkInternetConnection
import com.amrabdelhamiddiab.core.data.IResetPassword
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeoutOrNull

class ResetPasswordImpl(private val mAuth: FirebaseAuth, private val context: Context) :
    IResetPassword {
    override suspend fun resetPassword(email: String): Boolean {
        return if (checkInternetConnection(context)) {
            val result = withTimeoutOrNull(3000L) {
                try{
                    mAuth.sendPasswordResetEmail(email).await()
                    true
                }catch (ex: Exception){
                    Log.d(TAG, "ResetPasswordImpl : "+ ex.message.toString())
                    false
                }
            }
            result ?: false
        } else {
            Log.d(TAG,"ResetPasswordImpl : No Network")
            false
        }
    }
}