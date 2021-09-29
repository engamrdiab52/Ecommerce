package com.amrabdelhamiddiab.core.data

import com.amrabdelhamiddiab.core.domain.Bag

class RepositoryRemoveFavoriteItem(private val iRemoveFavoriteItem: IRemoveFavoriteItem) {
    suspend fun removeFavoriteItem(userId: String, bag: Bag) =
        iRemoveFavoriteItem.removeFavoriteItem(userId, bag)
}