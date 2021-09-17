package com.example.bags.framework

import android.app.Application
import android.util.Log
import com.example.bags.framework.firebase.*
import com.example.core.data.*
import com.example.core.useCases.*
import com.google.firebase.auth.FirebaseAuth

class BagsApplication : Application() {
    private var mAuth: FirebaseAuth? = null
    override fun onCreate() {
        super.onCreate()
        try {
            mAuth = FirebaseAuth.getInstance()
            if (mAuth != null){
                LoginFlowViewModelFactory.inject(
                    this, Interactions(
                        ResetUserPassword(RepositoryResetPassword(ResetPasswordImpl(mAuth!!))),
                        SendEmailVerification(
                            RepositorySendEmailVerification(
                                SendEmailVerificationImpl(
                                    mAuth!!
                                )
                            )
                        ),
                        SignUpUser(RepositorySignUpUser((SignUpUserImpl(mAuth!!)))),
                        SignOutUser(RepositorySignOutUser(SignOutUserImpl(mAuth!!))),
                        VerifyUserEmail(RepositoryVerifyEmail(VerifyUserEmailImpl(mAuth!!))),
                        SignInUser(RepositorySignInUser(SignInUserImpl(mAuth!!)))
                    )
                )
            }else{
                Log.d("MainActivity", "firebase Auth == null" )
            }
        }catch (e: Exception){
            Log.d("MainActivity", e.message.toString())
        }



    }
}