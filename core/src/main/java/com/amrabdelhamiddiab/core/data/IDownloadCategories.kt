package com.amrabdelhamiddiab.core.data

import com.amrabdelhamiddiab.core.domain.Category

interface IDownloadCategories {
    suspend fun downloadCategories(): List<Category>?
}