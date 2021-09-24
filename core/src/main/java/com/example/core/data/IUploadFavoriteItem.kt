package com.example.core.data

import com.example.core.domain.Bag

interface IUploadFavoriteItem {
    suspend fun uploadFavoriteItem(userId: String, bag: Bag):Boolean
}