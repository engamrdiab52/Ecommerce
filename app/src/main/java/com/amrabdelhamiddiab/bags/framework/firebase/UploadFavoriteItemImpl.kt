package com.amrabdelhamiddiab.bags.framework.firebase

import android.util.Log
import com.amrabdelhamiddiab.bags.MainActivity.Companion.TAG
import com.amrabdelhamiddiab.core.data.IUploadFavoriteItem
import com.amrabdelhamiddiab.core.domain.Bag
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class UploadFavoriteItemImpl(private val databaseReference: DatabaseReference) :
    IUploadFavoriteItem {
    override suspend fun uploadFavoriteItem(userId: String, bag: Bag): Boolean {
        return try {
            databaseReference.child("favorites").child(userId).child(bag.id_product!!).setValue(bag).await()
            true
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
            false
        }
    }
}