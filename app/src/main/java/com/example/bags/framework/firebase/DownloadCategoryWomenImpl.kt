package com.example.bags.framework.firebase

import android.util.Log
import com.example.bags.MainActivity
import com.example.bags.MainActivity.Companion.TAG
import com.example.core.data.IDownloadCategoryWomen
import com.example.core.domain.Bag
import com.example.core.domain.FavoriteOrder
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class DownloadCategoryWomenImpl(private val databaseReference: DatabaseReference):IDownloadCategoryWomen {
    private val _listOfWomenCategories: MutableList<Bag> = mutableListOf()
    private val listOfWomenCategories: List<Bag> = _listOfWomenCategories
    override suspend fun downloadCategoryWomen(): List<Bag>? {
        return try {
            _listOfWomenCategories.clear()
            val snapshot = databaseReference.child("categoryWomen").get().await()
            snapshot.children.forEach {
                val item = it.getValue(Bag::class.java)
                item?.let { it1 -> _listOfWomenCategories.add(it1) }
            }
            Log.d(TAG,"************"+ listOfWomenCategories.toString())
            listOfWomenCategories
        } catch (e: Exception) {
            Log.d(MainActivity.TAG, "********DownloadFavoritesImpl  :  " + e.message.toString())
            emptyList()
        }
    }
}