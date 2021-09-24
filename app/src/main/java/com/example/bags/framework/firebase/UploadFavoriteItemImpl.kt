package com.example.bags.framework.firebase

import android.util.Log
import com.example.bags.MainActivity.Companion.TAG
import com.example.core.data.IUploadFavoriteItem
import com.example.core.domain.Bag
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UploadFavoriteItemImpl(private val databaseReference: DatabaseReference) :
    IUploadFavoriteItem {
    override suspend fun uploadFavoriteItem(userId: String, bag: Bag): Boolean {
        return try {
            databaseReference.child("favorites").child(userId).child(bag.id_product!!).setValue(bag).await()
            Log.d(TAG,"UserId "+  userId)
            Log.d(TAG,"id_product "+  bag.id_product)
            true
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
            false
        }
    }
}