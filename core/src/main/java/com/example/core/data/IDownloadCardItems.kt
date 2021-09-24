package com.example.core.data

import com.example.core.domain.Bag

interface IDownloadCardItems {
    suspend fun downloadCardItems(): List<Bag>?
}