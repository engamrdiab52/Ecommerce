package com.amrabdelhamiddiab.core.data

import com.amrabdelhamiddiab.core.domain.Bag

class RepositoryUploadFavoriteItem(private val iUploadFavoriteItem: IUploadFavoriteItem) {
    suspend fun uploadFavoriteItem(userId: String, bag: Bag) =
        iUploadFavoriteItem.uploadFavoriteItem(userId, bag)
}