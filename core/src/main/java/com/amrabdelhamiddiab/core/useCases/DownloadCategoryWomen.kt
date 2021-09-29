package com.amrabdelhamiddiab.core.useCases

import com.amrabdelhamiddiab.core.data.RepositoryCategoryWomen

class DownloadCategoryWomen(private val repositoryCategoryWomen: RepositoryCategoryWomen) {
    suspend operator fun invoke() = repositoryCategoryWomen.downloadCategoryWomen()
}