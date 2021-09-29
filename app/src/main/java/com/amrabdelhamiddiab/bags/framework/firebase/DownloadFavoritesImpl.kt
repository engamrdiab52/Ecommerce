package com.amrabdelhamiddiab.bags.framework.firebase

import android.util.Log
import com.amrabdelhamiddiab.bags.MainActivity.Companion.TAG
import com.amrabdelhamiddiab.core.data.IDownloadFavorites
import com.amrabdelhamiddiab.core.domain.Bag
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class DownloadFavoritesImpl(private val databaseReference: DatabaseReference) : IDownloadFavorites {
    private val _listOfFavorites: MutableList<Bag> = mutableListOf()
    private val listOfFavorites: List<Bag> = _listOfFavorites
    override suspend fun downloadFavorites(): List<Bag> {
        return try {
            _listOfFavorites.clear()
            val userId: String? = FirebaseAuth.getInstance().currentUser?.uid
            val snapshot = userId?.let { databaseReference.child("favorites").child(it).get().await() }
            snapshot?.children?.forEach {
                val item = it.getValue(Bag::class.java)
                item?.let { it1 -> _listOfFavorites.add(it1) }
            }
                listOfFavorites

        } catch (e: Exception) {
            Log.d(TAG, "DownloadFavoritesImpl  :  " + e.message.toString())
            emptyList()
        }
    }
}