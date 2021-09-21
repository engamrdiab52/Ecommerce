package com.example.core.data

import com.example.core.domain.FavoriteOrder

interface IDownloadFavorites {
    suspend fun downloadFavorites(): List<FavoriteOrder>?
}