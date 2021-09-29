package com.amrabdelhamiddiab.core.data

class RepositoryDownloadCategories(private val iDownloadCategories: IDownloadCategories) {
    suspend fun downloadCategories() = iDownloadCategories.downloadCategories()
}