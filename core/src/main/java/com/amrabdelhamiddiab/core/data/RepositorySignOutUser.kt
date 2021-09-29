package com.amrabdelhamiddiab.core.data

class RepositorySignOutUser(private val iSignOutUser: ISignOutUser) {
    suspend fun signOutUserByFirebase() = iSignOutUser.signOutUser()
}