package com.amrabdelhamiddiab.core.useCases

import com.amrabdelhamiddiab.core.data.RepositoryDownloadCategories

class DownloadCategories(private val repositoryDownloadCategories: RepositoryDownloadCategories) {
    suspend operator fun invoke() = repositoryDownloadCategories.downloadCategories()
}