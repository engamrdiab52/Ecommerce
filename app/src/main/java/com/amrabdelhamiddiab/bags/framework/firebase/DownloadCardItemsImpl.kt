package com.amrabdelhamiddiab.bags.framework.firebase

import android.util.Log
import com.amrabdelhamiddiab.bags.MainActivity
import com.amrabdelhamiddiab.core.data.IDownloadCardItems
import com.amrabdelhamiddiab.core.domain.Bag
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class DownloadCardItemsImpl(private val databaseReference: DatabaseReference) : IDownloadCardItems {
    private val _listOfCardItems: MutableList<Bag> = mutableListOf()
    private val listOfCardItems: List<Bag> = _listOfCardItems

    override suspend fun downloadCardItems(): List<Bag>? {
        return try {
            _listOfCardItems.clear()
            val userId: String? = FirebaseAuth.getInstance().currentUser?.uid
            val snapshot = userId?.let { databaseReference.child("card_items").child(it).get().await() }
            snapshot?.children?.forEach {
                val item = it.getValue(Bag::class.java)
                item?.let { it1 -> _listOfCardItems.add(it1) }
            }
            listOfCardItems

        } catch (e: Exception) {
            Log.d(MainActivity.TAG, "DownloadFavoritesImpl  :  " + e.message.toString())
            emptyList()
        }
    }
}