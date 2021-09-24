package com.example.core.data

import com.example.core.domain.Bag

interface IRemoveCardItem {
    suspend fun removeCardItem(userId: String, bag: Bag):Boolean
}