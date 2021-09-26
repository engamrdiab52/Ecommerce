package com.example.bags.presentation.cart

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bags.MainActivity
import com.example.bags.framework.BagsViewModel
import com.example.bags.framework.Interactions
import com.example.bags.framework.utilis.SingleLiveEvent
import com.example.core.domain.Bag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application, dependencies: Interactions) :
    BagsViewModel(application, dependencies) {
    private val _listOfCartItems = SingleLiveEvent<List<Bag>>()
    val listOfCartItems: LiveData<List<Bag>> get() = _listOfCartItems

    private val _downloading = SingleLiveEvent<Boolean>()
    val downloading: LiveData<Boolean> get() = _downloading

    private val _cardClicked = SingleLiveEvent<Boolean>()
    val cardClicked: LiveData<Boolean> get() = _cardClicked

    private val _cartBag = SingleLiveEvent<Bag?>()
    val cartBag: LiveData<Bag?> get() = _cartBag

    private val _uploadTask = SingleLiveEvent<Boolean>()
    val uploadTask: LiveData<Boolean> get() = _uploadTask

    fun downloadCartItems() {
        viewModelScope.launch(Dispatchers.IO) {
            _downloading.postValue(true)
            _listOfCartItems.postValue(dependencies.downloadCardItems())
            _downloading.postValue(false)
        }
    }
    fun buttonGoToCartDetailsClicked(){
        _cardClicked.value = true
    }
    fun addIdValue(bag: Bag?){
        _cartBag.value = bag
    }
    fun removeItem(userId:String, bag: Bag){
        viewModelScope.launch(Dispatchers.IO){
            _uploadTask.postValue(dependencies.removeCardItem(userId, bag))
        }
    }
    fun addItemToCartList(userId:String, bag: Bag){
        viewModelScope.launch(Dispatchers.IO){
            _uploadTask.postValue(dependencies.uploadCardItem(userId, bag))
        }

    }
    fun search(query :String?, tempList : List<Bag>){
        viewModelScope.launch(Dispatchers.IO){
            //  val listOfTempBags = listOfCategoryWomen.value
            val filteredList = tempList.filter {
                query?.let { it1 ->
                    it.name_product?.contains(
                        it1, true
                    )
                }!!
            }
            _listOfCartItems.postValue(filteredList)
            Log.d(MainActivity.TAG, filteredList.toString())
        }
    }
}