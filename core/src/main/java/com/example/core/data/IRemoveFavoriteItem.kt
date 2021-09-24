package com.example.core.data

import com.example.core.domain.Bag

interface IRemoveFavoriteItem {
    suspend fun removeFavoriteItem(userId: String, bag: Bag):Boolean
}