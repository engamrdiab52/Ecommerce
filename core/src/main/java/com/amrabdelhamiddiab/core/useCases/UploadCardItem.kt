package com.amrabdelhamiddiab.core.useCases

import com.amrabdelhamiddiab.core.data.RepositoryUploadCardItem
import com.amrabdelhamiddiab.core.domain.Bag

class UploadCardItem(private val repositoryUploadCardItem: RepositoryUploadCardItem) {
    suspend operator fun invoke(userId: String, bag: Bag) =
        repositoryUploadCardItem.uploadCardItem(userId, bag)
}