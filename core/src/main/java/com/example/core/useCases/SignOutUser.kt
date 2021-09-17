package com.example.core.useCases

import com.example.core.data.RepositorySignOutUser

class SignOutUser(private val repositorySignOutUser: RepositorySignOutUser) {
    suspend operator fun invoke() = repositorySignOutUser.signOutUserByFirebase()
}