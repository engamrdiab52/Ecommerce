package com.amrabdelhamiddiab.core.data

import com.amrabdelhamiddiab.core.domain.Bag

interface IUploadFavoriteItem {
    suspend fun uploadFavoriteItem(userId: String, bag: Bag):Boolean
}