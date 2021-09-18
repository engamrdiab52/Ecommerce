package com.example.core.data

import com.example.core.domain.User

interface ISignupUser {
    suspend fun signupUser(email: String, password: String):Boolean
  //  suspend fun userLoggedIn(): Boolean
}