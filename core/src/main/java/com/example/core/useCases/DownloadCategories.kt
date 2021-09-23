package com.example.core.useCases

import com.example.core.data.RepositoryDownloadCategories

class DownloadCategories(private val repositoryDownloadCategories: RepositoryDownloadCategories) {
    suspend operator fun invoke() = repositoryDownloadCategories.downloadCategories()
}