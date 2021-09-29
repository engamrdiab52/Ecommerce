package com.amrabdelhamiddiab.core.data


interface ISignInUser {
    suspend fun signInUser(email: String, password: String):Boolean
}