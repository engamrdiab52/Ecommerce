package com.amrabdelhamiddiab.bags.framework.firebase

import android.util.Log
import com.amrabdelhamiddiab.bags.MainActivity
import com.amrabdelhamiddiab.core.data.IUploadCardItem
import com.amrabdelhamiddiab.core.domain.Bag
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class UploadCardItemImpl(private val databaseReference: DatabaseReference): IUploadCardItem {
    override suspend fun uploadCardItem(userId: String, bag: Bag): Boolean {
        return try {
            databaseReference.child("card_items").child(userId).child(bag.id_product!!).setValue(bag).await()
            true
        } catch (e: Exception) {
            Log.d(MainActivity.TAG, e.message.toString())
            false
        }
    }
}