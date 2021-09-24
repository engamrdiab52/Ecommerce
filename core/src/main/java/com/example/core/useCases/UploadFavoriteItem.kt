package com.example.core.useCases

import com.example.core.data.RepositoryUploadFavoriteItem
import com.example.core.domain.Bag

class UploadFavoriteItem(private val repositoryUploadFavoriteItem: RepositoryUploadFavoriteItem) {
    suspend operator fun invoke(userId: String, bag: Bag) =
        repositoryUploadFavoriteItem.uploadFavoriteItem(userId, bag)
}