package com.example.bags.presentation.favorite

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bags.MainActivity
import com.example.bags.framework.BagsViewModel
import com.example.bags.framework.Interactions
import com.example.bags.framework.LoginFlowViewModelFactory.application
import com.example.bags.framework.LoginFlowViewModelFactory.dependencies
import com.example.bags.framework.utilis.SingleLiveEvent
import com.example.core.domain.Bag
import com.example.core.domain.FavoriteOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application, dependencies: Interactions) :
    BagsViewModel(application, dependencies) {
    private val _listOfFavorites = SingleLiveEvent<List<Bag>>()
    val listOfFavorites: LiveData<List<Bag>> get() = _listOfFavorites

    private val _downloading = SingleLiveEvent<Boolean>()
    val downloading: LiveData<Boolean> get() = _downloading

    private val _cardClicked = SingleLiveEvent<Boolean>()
    val cardClicked: LiveData<Boolean> get() = _cardClicked

    private val _favoriteBag = SingleLiveEvent<Bag?>()
    val favoriteBag: LiveData<Bag?> get() = _favoriteBag

    private val _uploadTask = SingleLiveEvent<Boolean>()
    val uploadTask: LiveData<Boolean> get() = _uploadTask

    fun downloadFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            _downloading.postValue(true)
            _listOfFavorites.postValue(dependencies.downloadFavorites())
            _downloading.postValue(false)
        }
    }
    fun buttonGoToFavoritesDetailsClicked(){
        _cardClicked.value = true
    }
    fun addIdValue(bag: Bag?){
        _favoriteBag.value = bag
    }

    fun removeItem(userId:String, bag: Bag){
        viewModelScope.launch(Dispatchers.IO){
            _uploadTask.postValue(dependencies.removeFavoriteItem(userId, bag))
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
            _listOfFavorites.postValue(filteredList)
            Log.d(MainActivity.TAG, filteredList.toString())
        }
    }
}