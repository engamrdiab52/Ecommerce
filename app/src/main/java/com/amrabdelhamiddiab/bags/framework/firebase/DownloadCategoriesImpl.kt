package com.amrabdelhamiddiab.bags.framework.firebase

import com.amrabdelhamiddiab.core.data.IDownloadCategories
import com.amrabdelhamiddiab.core.domain.Category
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class DownloadCategoriesImpl(private val databaseReference: DatabaseReference) :
    IDownloadCategories {
    private val _listOfCategories: MutableList<Category> = mutableListOf()
    private val listOfCategories: List<Category> = _listOfCategories
    override suspend fun downloadCategories(): List<Category>? {
        return try {
            _listOfCategories.clear()
            val snapshot = databaseReference.child("categories").get().await()
            snapshot.children.forEach {
                val item = it.getValue(Category::class.java)
                item?.let { it1 -> _listOfCategories.add(it1) }
            }
            listOfCategories
        } catch (e: Exception) {
            emptyList()
        }
    }
}