package com.amrabdelhamiddiab.core.data

import com.amrabdelhamiddiab.core.domain.Bag

class RepositoryUploadCardItem(private val iUploadCardItem: IUploadCardItem) {
    suspend fun uploadCardItem(userId: String, bag: Bag) =
        iUploadCardItem.uploadCardItem(userId, bag)
}