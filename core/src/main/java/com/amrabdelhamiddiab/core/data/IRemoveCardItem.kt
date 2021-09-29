package com.amrabdelhamiddiab.core.data

import com.amrabdelhamiddiab.core.domain.Bag

interface IRemoveCardItem {
    suspend fun removeCardItem(userId: String, bag: Bag):Boolean
}