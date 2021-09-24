package com.example.bags.framework.firebase

import android.util.Log
import com.example.bags.MainActivity
import com.example.core.data.IRemoveFavoriteItem
import com.example.core.domain.Bag
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class RemoveFavoriteItemImpl(private val databaseReference: DatabaseReference) : IRemoveFavoriteItem  {
    override suspend fun removeFavoriteItem(userId: String, bag: Bag): Boolean {
        return try {
            databaseReference.child("favorites").child(userId).child(bag.id_product!!).removeValue().await()
            Log.d(MainActivity.TAG,"UserId "+  userId)
            Log.d(MainActivity.TAG,"id_product "+  bag.id_product)
            true
        } catch (e: Exception) {
            Log.d(MainActivity.TAG, e.message.toString())
            false
        }
    }
}