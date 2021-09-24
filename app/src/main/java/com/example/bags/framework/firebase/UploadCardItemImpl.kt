package com.example.bags.framework.firebase

import android.util.Log
import com.example.bags.MainActivity
import com.example.core.data.IUploadCardItem
import com.example.core.domain.Bag
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class UploadCardItemImpl(private val databaseReference: DatabaseReference): IUploadCardItem {
    override suspend fun uploadCardItem(userId: String, bag: Bag): Boolean {
        return try {
            databaseReference.child("card_items").child(userId).child(bag.id_product!!).setValue(bag).await()
            Log.d(MainActivity.TAG,"UserId "+  userId)
            Log.d(MainActivity.TAG,"id_product "+  bag.id_product)
            true
        } catch (e: Exception) {
            Log.d(MainActivity.TAG, e.message.toString())
            false
        }
    }
}