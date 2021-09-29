package com.amrabdelhamiddiab.core.useCases

import com.amrabdelhamiddiab.core.data.RepositorySignInUser

class SignInUser(private val repositorySignInUser: RepositorySignInUser) {
    suspend operator fun invoke(email: String, password: String) =
        repositorySignInUser.signInUserByFirebase(email, password)
}