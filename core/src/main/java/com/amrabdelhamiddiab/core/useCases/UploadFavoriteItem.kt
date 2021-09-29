package com.amrabdelhamiddiab.core.useCases

import com.amrabdelhamiddiab.core.data.RepositoryUploadFavoriteItem
import com.amrabdelhamiddiab.core.domain.Bag

class UploadFavoriteItem(private val repositoryUploadFavoriteItem: RepositoryUploadFavoriteItem) {
    suspend operator fun invoke(userId: String, bag: Bag) =
        repositoryUploadFavoriteItem.uploadFavoriteItem(userId, bag)
}