package com.amrabdelhamiddiab.core.data

interface ISignupUser {
    suspend fun signupUser(email: String, password: String):Boolean
  //  suspend fun userLoggedIn(): Boolean
}