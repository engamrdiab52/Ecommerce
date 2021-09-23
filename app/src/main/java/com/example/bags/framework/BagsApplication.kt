package com.example.bags.framework

import android.app.Application
import android.util.Log
import com.example.bags.framework.firebase.*
import com.example.core.data.*
import com.example.core.useCases.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BagsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.database.setPersistenceEnabled(true)
        val databaseReference = Firebase.database.reference
        val mAuth = FirebaseAuth.getInstance()
        LoginFlowViewModelFactory.inject(
            this, Interactions(
                ResetUserPassword(RepositoryResetPassword(ResetPasswordImpl(mAuth,this.applicationContext))),
                SendEmailVerification(
                    RepositorySendEmailVerification(
                        SendEmailVerificationImpl(
                            mAuth
                        )
                    )
                ),
                SignUpUser(RepositorySignUpUser((SignUpUserImpl(mAuth)))),
                SignOutUser(RepositorySignOutUser(SignOutUserImpl(mAuth))),
                VerifyUserEmail(RepositoryVerifyEmail(VerifyUserEmailImpl(mAuth))),
                SignInUser(RepositorySignInUser(SignInUserImpl(mAuth))),
                EmailVerifiedState(RepositoryEmailVerifiedState(EmailVerifiesStateImpl(mAuth, this.applicationContext))),
                DownloadFavorites(RepositoryDownloadFavorites(DownloadFavoritesImpl(databaseReference))),
                DownloadCategoryWomen(RepositoryCategoryWomen(DownloadCategoryWomenImpl(databaseReference))),
                DownloadCategories(RepositoryDownloadCategories(DownloadCategoriesImpl(databaseReference)))
            )
        )
    }
}
// UserLoggedIn(RepositorySignUpUser(SignUpUserImpl(mAuth)))