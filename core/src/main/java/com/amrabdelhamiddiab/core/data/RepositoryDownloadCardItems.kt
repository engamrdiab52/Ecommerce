package com.amrabdelhamiddiab.core.data

class RepositoryDownloadCardItems(private val iDownloadCardItems: IDownloadCardItems) {
    suspend fun downloadCardItems() = iDownloadCardItems.downloadCardItems()
}