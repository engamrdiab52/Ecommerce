package com.example.core.useCases

import com.example.core.data.RepositoryRemoveCardItem
import com.example.core.domain.Bag

class RemoveCardItem(private val repositoryRemoveCardItem: RepositoryRemoveCardItem) {
    suspend operator fun invoke(userId: String, bag: Bag) =
        repositoryRemoveCardItem.removeCardItem(userId, bag)
}