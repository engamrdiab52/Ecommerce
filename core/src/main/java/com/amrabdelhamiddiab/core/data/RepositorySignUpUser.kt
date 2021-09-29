package com.amrabdelhamiddiab.core.data

class RepositorySignUpUser(private val iSignupUser: ISignupUser) {
    suspend fun signUpUserByFirebase(email: String, password: String) =
        iSignupUser.signupUser(email, password)
}