package com.example.core.data

import com.example.core.domain.Bag

interface IUploadCardItem {
    suspend fun uploadCardItem(userId: String, bag: Bag):Boolean
}