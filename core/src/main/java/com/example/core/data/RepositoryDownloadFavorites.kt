package com.example.core.data

class RepositoryDownloadFavorites(private val iDownloadFavorites: IDownloadFavorites) {
    suspend fun downloadFavorites() = iDownloadFavorites.downloadFavorites()
}