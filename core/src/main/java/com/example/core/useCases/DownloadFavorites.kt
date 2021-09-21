package com.example.core.useCases

import com.example.core.data.RepositoryDownloadFavorites

class DownloadFavorites(private val repositoryDownloadFavorites: RepositoryDownloadFavorites) {
    suspend operator fun invoke() = repositoryDownloadFavorites.downloadFavorites()
}