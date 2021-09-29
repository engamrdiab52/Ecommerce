package com.amrabdelhamiddiab.bags.framework.firebase

import com.amrabdelhamiddiab.core.data.IRemoveFavoriteItem
import com.amrabdelhamiddiab.core.domain.Bag
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class RemoveFavoriteItemImpl(private val databaseReference: DatabaseReference) : IRemoveFavoriteItem  {
    override suspend fun removeFavoriteItem(userId: String, bag: Bag): Boolean {
        return try {
            databaseReference.child("favorites").child(userId).child(bag.id_product!!).removeValue().await()
            true
        } catch (e: Exception) {
            false
        }
    }
}