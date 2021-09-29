package com.amrabdelhamiddiab.core.useCases

import com.amrabdelhamiddiab.core.data.RepositorySignOutUser

class SignOutUser(private val repositorySignOutUser: RepositorySignOutUser) {
    suspend operator fun invoke() = repositorySignOutUser.signOutUserByFirebase()
}