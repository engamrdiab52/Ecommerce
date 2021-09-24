package com.example.core.useCases

import com.example.core.data.RepositoryDownloadCardItems

class DownloadCardItems(private val repositoryDownloadCardItems: RepositoryDownloadCardItems) {
    suspend operator fun invoke() = repositoryDownloadCardItems.downloadCardItems()
}