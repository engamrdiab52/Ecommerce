package com.example.bags.presentation.categories.women

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bags.MainActivity
import com.example.bags.framework.BagsViewModel
import com.example.bags.framework.Interactions
import com.example.bags.framework.utilis.SingleLiveEvent
import com.example.core.domain.Bag
import com.example.core.domain.FavoriteOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryWomenViewModel(application: Application, dependencies: Interactions):
    BagsViewModel(application, dependencies) {
    private val _listOfCategoryWomen = SingleLiveEvent<List<Bag>>()
    val listOfCategoryWomen: LiveData<List<Bag>> get() = _listOfCategoryWomen

    private val _downloading = SingleLiveEvent<Boolean>()
    val downloading: LiveData<Boolean> get() = _downloading

    private val _cardClicked = SingleLiveEvent<Boolean>()
    val cardClicked: LiveData<Boolean> get() = _cardClicked

    private val _bag = SingleLiveEvent<Bag?>()
    val bag: LiveData<Bag?> get() = _bag

    private val _uploadTask = SingleLiveEvent<Boolean>()
    val uploadTask: LiveData<Boolean> get() = _uploadTask

    val tempList: List<Bag>? = listOfCategoryWomen.value


    fun downloadCategoryWomen(){
        viewModelScope.launch(Dispatchers.IO){
            _downloading.postValue(true)
            _listOfCategoryWomen.postValue(dependencies.downloadCategoryWomen())
            _downloading.postValue(false)
        }
    }
    fun buttonGoToDetailsClicked(){
        _cardClicked.value = true
    }
    fun addIdValue(bag: Bag?){
        _bag.value = bag
    }
    fun uploadFavoriteItem(userId:String, bag: Bag){
        viewModelScope.launch(Dispatchers.IO){
            _uploadTask.postValue(dependencies.uploadFavoriteItem(userId, bag))
        }
    }
    fun addItemToCartList(userId:String, bag: Bag){
        viewModelScope.launch(Dispatchers.IO){
            dependencies.uploadCardItem(userId, bag)
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
            _listOfCategoryWomen.postValue(filteredList)
            Log.d(MainActivity.TAG, filteredList.toString())
        }
    }
}