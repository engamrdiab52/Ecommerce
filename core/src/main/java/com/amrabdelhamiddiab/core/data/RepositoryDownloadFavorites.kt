package com.amrabdelhamiddiab.core.data

class RepositoryDownloadFavorites(private val iDownloadFavorites: IDownloadFavorites) {
    suspend fun downloadFavorites() = iDownloadFavorites.downloadFavorites()
}