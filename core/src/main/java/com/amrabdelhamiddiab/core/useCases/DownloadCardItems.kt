package com.amrabdelhamiddiab.core.useCases

import com.amrabdelhamiddiab.core.data.RepositoryDownloadCardItems

class DownloadCardItems(private val repositoryDownloadCardItems: RepositoryDownloadCardItems) {
    suspend operator fun invoke() = repositoryDownloadCardItems.downloadCardItems()
}