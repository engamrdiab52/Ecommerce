package com.amrabdelhamiddiab.core.data

import com.amrabdelhamiddiab.core.domain.Bag

interface IRemoveFavoriteItem {
    suspend fun removeFavoriteItem(userId: String, bag: Bag):Boolean
}