package com.example.core.useCases

import com.example.core.data.RepositoryCategoryWomen

class DownloadCategoryWomen(private val repositoryCategoryWomen: RepositoryCategoryWomen) {
    suspend operator fun invoke() = repositoryCategoryWomen.downloadCategoryWomen()
}