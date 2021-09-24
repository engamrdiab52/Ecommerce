package com.example.core.data

import com.example.core.domain.Bag

class RepositoryRemoveFavoriteItem(private val iRemoveFavoriteItem: IRemoveFavoriteItem) {
    suspend fun removeFavoriteItem(userId: String, bag: Bag) =
        iRemoveFavoriteItem.removeFavoriteItem(userId, bag)
}