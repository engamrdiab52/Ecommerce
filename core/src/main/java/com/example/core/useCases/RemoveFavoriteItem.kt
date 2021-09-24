package com.example.core.useCases

import com.example.core.data.RepositoryRemoveFavoriteItem
import com.example.core.domain.Bag

class RemoveFavoriteItem(private val repositoryRemoveFavoriteItem: RepositoryRemoveFavoriteItem) {
    suspend operator fun invoke(userId: String, bag: Bag) =
        repositoryRemoveFavoriteItem.removeFavoriteItem(userId, bag)
}