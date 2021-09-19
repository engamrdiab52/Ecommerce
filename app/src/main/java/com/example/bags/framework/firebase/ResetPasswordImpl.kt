package com.example.bags.framework.firebase

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.bags.MainActivity.Companion.TAG
import com.example.bags.framework.utilis.checkInternetConnection
import com.example.core.data.IResetPassword
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeoutOrNull

class ResetPasswordImpl(private val mAuth: FirebaseAuth, private val context: Context) :
    IResetPassword {
    override suspend fun resetPassword(email: String): Boolean {
        /*     return try {
                 mAuth.sendPasswordResetEmail(email).await()
                 Log.d(TAG, "555555555555")
                 true
             } catch (ex: Exception) {
                 Log.d(TAG,"33333333333333" +ex.message.toString())
                 false
             }*/
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