package com.example.core.data

import com.example.core.domain.Bag
import com.example.core.domain.FavoriteOrder

interface IDownloadFavorites {
    suspend fun downloadFavorites(): List<Bag>?
}