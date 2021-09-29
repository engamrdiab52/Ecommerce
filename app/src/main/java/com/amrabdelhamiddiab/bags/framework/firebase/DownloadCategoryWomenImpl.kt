package com.amrabdelhamiddiab.bags.framework.firebase

import com.amrabdelhamiddiab.core.data.IDownloadCategoryWomen
import com.amrabdelhamiddiab.core.domain.Bag
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
            listOfWomenCategories
        } catch (e: Exception) {
            emptyList()
        }
    }
}