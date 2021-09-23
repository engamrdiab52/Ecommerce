package com.example.bags.presentation.categories.women

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bags.framework.BagsViewModel
import com.example.bags.framework.Interactions
import com.example.bags.framework.utilis.SingleLiveEvent
import com.example.core.domain.Bag
import com.example.core.domain.FavoriteOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryWomenViewModel(application: Application, dependencies: Interactions):
    BagsViewModel(application, dependencies) {
    private val _listOfCategoryWomen = MutableLiveData<List<Bag>>()
    val listOfCategoryWomen: LiveData<List<Bag>> get() = _listOfCategoryWomen

    private val _downloading = SingleLiveEvent<Boolean>()
    val downloading: LiveData<Boolean> get() = _downloading

    fun downloadCategoryWomen(){
        viewModelScope.launch(Dispatchers.IO){
            _downloading.postValue(true)
            _listOfCategoryWomen.postValue(dependencies.downloadCategoryWomen())
            _downloading.postValue(false)
        }
    }
}