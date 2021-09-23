package com.example.core.data

class RepositoryCategoryWomen(private val iDownloadCategoryWomen: IDownloadCategoryWomen) {
    suspend fun downloadCategoryWomen() = iDownloadCategoryWomen.downloadCategoryWomen()
}