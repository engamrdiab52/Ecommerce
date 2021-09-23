package com.example.bags.presentation.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bags.framework.BagsViewModel
import com.example.bags.framework.Interactions
import com.example.bags.framework.utilis.SingleLiveEvent
import com.example.core.domain.Bag
import com.example.core.domain.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application, dependencies: Interactions):
    BagsViewModel(application, dependencies) {
    private val _listOfCategories = MutableLiveData<List<Category>>()
    val listOfCategories: LiveData<List<Category>> get() = _listOfCategories

    private val _downloading = SingleLiveEvent<Boolean>()
    val downloading: LiveData<Boolean> get() = _downloading

    private val _cardClicked = SingleLiveEvent<Boolean>()
    val cardClicked: LiveData<Boolean> get() = _cardClicked

    fun downloadCategories(){
        viewModelScope.launch(Dispatchers.IO){
            _downloading.postValue(true)
            _listOfCategories.postValue(dependencies.downloadCategories())
            _downloading.postValue(false)
        }
    }
    fun buttonClicked(){
        _cardClicked.value = true
    }
}