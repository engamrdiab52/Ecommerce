package com.amrabdelhamiddiab.core.useCases

import com.amrabdelhamiddiab.core.data.RepositorySignUpUser

class SignUpUser(private val repositorySignUpUser: RepositorySignUpUser) {
    suspend operator fun invoke(email: String, password: String) =
        repositorySignUpUser.signUpUserByFirebase(email, password)
}