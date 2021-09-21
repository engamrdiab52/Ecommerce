package com.example.bags.framework.firebase

import android.util.Log
import com.example.bags.MainActivity.Companion.TAG
import com.example.core.data.IDownloadFavorites
import com.example.core.domain.FavoriteOrder
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class DownloadFavoritesImpl(private val databaseReference: DatabaseReference) : IDownloadFavorites {
    private val _listOfFavorites: MutableList<FavoriteOrder> = mutableListOf()
    private val listOfFavorites: List<FavoriteOrder> = _listOfFavorites
    override suspend fun downloadFavorites(): List<FavoriteOrder> {
        return try {
            _listOfFavorites.clear()
            val snapshot = databaseReference.get().await()
                snapshot.children.forEach {
                    val item = it.getValue(FavoriteOrder::class.java)
                    item?.let { it1 -> _listOfFavorites.add(it1) }
                }
                listOfFavorites

        } catch (e: Exception) {
            Log.d(TAG, "DownloadFavoritesImpl  :  " + e.message.toString())
            emptyList()
        }
    }
}