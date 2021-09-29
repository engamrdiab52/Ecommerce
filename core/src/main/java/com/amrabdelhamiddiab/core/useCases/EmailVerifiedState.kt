package com.amrabdelhamiddiab.core.useCases

import com.amrabdelhamiddiab.core.data.RepositoryEmailVerifiedState

class EmailVerifiedState(private val repositoryEmailVerifiedState: RepositoryEmailVerifiedState) {
    suspend operator fun invoke() = repositoryEmailVerifiedState.isEmailVerified()

}