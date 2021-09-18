package com.example.core.useCases

import com.example.core.data.RepositoryEmailVerifiedState

class EmailVerifiedState(private val repositoryEmailVerifiedState: RepositoryEmailVerifiedState) {
    suspend operator fun invoke() = repositoryEmailVerifiedState.isEmailVerified()

}