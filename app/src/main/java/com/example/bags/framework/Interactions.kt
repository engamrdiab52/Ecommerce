package com.example.bags.framework

import com.example.core.useCases.*

data class Interactions(
    val resetUserPassword: ResetUserPassword,
    val sendEmailVerification: SendEmailVerification,
    val signUpUser: SignUpUser,
    val signOutUser: SignOutUser,
    val verifyUserEmail: VerifyUserEmail,
    val signInUser: SignInUser
)