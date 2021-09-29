package com.amrabdelhamiddiab.core.data

import com.amrabdelhamiddiab.core.domain.Bag

class RepositoryRemoveCardItem(private val iRemoveCardItem: IRemoveCardItem) {
    suspend fun removeCardItem(userId: String, bag: Bag) =
        iRemoveCardItem.removeCardItem(userId, bag)
}