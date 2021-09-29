package com.amrabdelhamiddiab.core.useCases

import com.amrabdelhamiddiab.core.data.RepositoryDownloadFavorites

class DownloadFavorites(private val repositoryDownloadFavorites: RepositoryDownloadFavorites) {
    suspend operator fun invoke() = repositoryDownloadFavorites.downloadFavorites()
}