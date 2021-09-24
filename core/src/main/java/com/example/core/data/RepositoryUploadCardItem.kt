package com.example.core.data

import com.example.core.domain.Bag

class RepositoryUploadCardItem(private val iUploadCardItem: IUploadCardItem) {
    suspend fun uploadCardItem(userId: String, bag: Bag) =
        iUploadCardItem.uploadCardItem(userId, bag)
}