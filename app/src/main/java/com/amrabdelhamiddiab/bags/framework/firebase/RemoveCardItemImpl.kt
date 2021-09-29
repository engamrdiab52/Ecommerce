package com.amrabdelhamiddiab.bags.framework.firebase

import android.util.Log
import com.amrabdelhamiddiab.bags.MainActivity
import com.amrabdelhamiddiab.core.data.IRemoveCardItem
import com.amrabdelhamiddiab.core.domain.Bag
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class RemoveCardItemImpl(private val databaseReference: DatabaseReference) : IRemoveCardItem {
    override suspend fun removeCardItem(userId: String, bag: Bag): Boolean {
        return try {
            databaseReference.child("card_items").child(userId).child(bag.id_product!!)
                .removeValue().await()
            true
        } catch (e: Exception) {
            Log.d(MainActivity.TAG, e.message.toString())
            false
        }
    }
}