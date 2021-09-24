package com.example.core.useCases

import com.example.core.data.RepositoryUploadCardItem
import com.example.core.domain.Bag

class UploadCardItem(private val repositoryUploadCardItem: RepositoryUploadCardItem) {
    suspend operator fun invoke(userId: String, bag: Bag) =
        repositoryUploadCardItem.uploadCardItem(userId, bag)
}