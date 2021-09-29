package com.amrabdelhamiddiab.core.data

import com.amrabdelhamiddiab.core.domain.Bag

interface IDownloadCardItems {
    suspend fun downloadCardItems(): List<Bag>?
}