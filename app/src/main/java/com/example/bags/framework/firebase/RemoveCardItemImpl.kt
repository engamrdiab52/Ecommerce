package com.example.bags.framework.firebase

import android.util.Log
import com.example.bags.MainActivity
import com.example.core.data.IRemoveCardItem
import com.example.core.domain.Bag
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class RemoveCardItemImpl(private val databaseReference: DatabaseReference) : IRemoveCardItem {
    override suspend fun removeCardItem(userId: String, bag: Bag): Boolean {
        return try {
            databaseReference.child("card_items").child(userId).child(bag.id_product!!)
                .removeValue().await()
            Log.d(MainActivity.TAG, "UserId " + userId)
            Log.d(MainActivity.TAG, "id_product " + bag.id_product)
            true
        } catch (e: Exception) {
            Log.d(MainActivity.TAG, e.message.toString())
            false
        }
    }
}