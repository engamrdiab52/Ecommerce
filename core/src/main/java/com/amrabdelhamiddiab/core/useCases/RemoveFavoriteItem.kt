package com.amrabdelhamiddiab.core.useCases

import com.amrabdelhamiddiab.core.data.RepositoryRemoveFavoriteItem
import com.amrabdelhamiddiab.core.domain.Bag

class RemoveFavoriteItem(private val repositoryRemoveFavoriteItem: RepositoryRemoveFavoriteItem) {
    suspend operator fun invoke(userId: String, bag: Bag) =
        repositoryRemoveFavoriteItem.removeFavoriteItem(userId, bag)
}