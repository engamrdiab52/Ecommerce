package com.example.core.data

class RepositorySignInUser(private val iSignInUser: ISignInUser) {
    suspend fun signInUserByFirebase(email: String, password: String) =
        iSignInUser.signInUser(email, password)

}