package com.example.bags.framework.firebase

import android.util.Log
import com.example.bags.MainActivity
import com.example.core.data.IDownloadCategories
import com.example.core.domain.Bag
import com.example.core.domain.Category
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class DownloadCategoriesImpl(private val databaseReference: DatabaseReference) :
    IDownloadCategories {
    private val _listOfCategories: MutableList<Category> = mutableListOf()
    private val listOfCategories: List<Category> = _listOfCategories
    override suspend fun downloadCategories(): List<Category>? {
        return try {
            _listOfCategories.clear()
            val snapshot = databaseReference.child("categoryWomen").get().await()
            snapshot.children.forEach {
                val item = it.getValue(Category::class.java)
                item?.let { it1 -> _listOfCategories.add(it1) }
            }
            Log.d(MainActivity.TAG,"************"+ listOfCategories.toString())
            listOfCategories
        } catch (e: Exception) {
            Log.d(MainActivity.TAG, "********DownloadFavoritesImpl  :  " + e.message.toString())
            emptyList()
        }
    }
}