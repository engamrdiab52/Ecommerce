package com.example.core.data

class RepositoryDownloadCardItems(private val iDownloadCardItems: IDownloadCardItems) {
    suspend fun downloadCardItems() = iDownloadCardItems.downloadCardItems()
}