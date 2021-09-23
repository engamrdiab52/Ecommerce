package com.example.core.data

import com.example.core.domain.Category

interface IDownloadCategories {
    suspend fun downloadCategories(): List<Category>?
}