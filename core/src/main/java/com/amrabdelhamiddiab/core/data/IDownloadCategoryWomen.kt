package com.amrabdelhamiddiab.core.data

import com.amrabdelhamiddiab.core.domain.Bag

interface IDownloadCategoryWomen {
    suspend fun downloadCategoryWomen(): List<Bag>?
}