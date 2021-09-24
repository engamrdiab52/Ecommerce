package com.example.core.data

import com.example.core.domain.Bag

class RepositoryUploadFavoriteItem(private val iUploadFavoriteItem: IUploadFavoriteItem) {
    suspend fun uploadFavoriteItem(userId: String, bag: Bag) =
        iUploadFavoriteItem.uploadFavoriteItem(userId, bag)
}