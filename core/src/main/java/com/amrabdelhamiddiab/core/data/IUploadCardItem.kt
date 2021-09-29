package com.amrabdelhamiddiab.core.data

import com.amrabdelhamiddiab.core.domain.Bag

interface IUploadCardItem {
    suspend fun uploadCardItem(userId: String, bag: Bag):Boolean
}