package com.amrabdelhamiddiab.core.useCases

import com.amrabdelhamiddiab.core.data.RepositoryRemoveCardItem
import com.amrabdelhamiddiab.core.domain.Bag

class RemoveCardItem(private val repositoryRemoveCardItem: RepositoryRemoveCardItem) {
    suspend operator fun invoke(userId: String, bag: Bag) =
        repositoryRemoveCardItem.removeCardItem(userId, bag)
}