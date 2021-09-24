package com.example.core.data

import com.example.core.domain.Bag

class RepositoryRemoveCardItem(private val iRemoveCardItem: IRemoveCardItem) {
    suspend fun removeCardItem(userId: String, bag: Bag) =
        iRemoveCardItem.removeCardItem(userId, bag)
}