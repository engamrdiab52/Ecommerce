package com.example.core.data

import com.example.core.domain.Bag
import com.example.core.domain.FavoriteOrder

interface IDownloadCategoryWomen {
    suspend fun downloadCategoryWomen(): List<Bag>?
}