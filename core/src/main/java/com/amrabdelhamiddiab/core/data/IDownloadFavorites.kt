package com.amrabdelhamiddiab.core.data

import com.amrabdelhamiddiab.core.domain.Bag

interface IDownloadFavorites {
    suspend fun downloadFavorites(): List<Bag>?
}