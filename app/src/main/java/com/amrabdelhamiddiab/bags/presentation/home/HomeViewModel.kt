package com.amrabdelhamiddiab.bags.presentation.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.amrabdelhamiddiab.bags.framework.BagsViewModel
import com.amrabdelhamiddiab.bags.framework.Interactions
import com.amrabdelhamiddiab.bags.framework.utilis.SingleLiveEvent
import com.amrabdelhamiddiab.core.domain.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application, dependencies: Interactions):
    BagsViewModel(application, dependencies) {
    private val _listOfCategories = SingleLiveEvent<List<Category>?>()
    val listOfCategories: LiveData<List<Category>?> get() = _listOfCategories

    private val _downloading = SingleLiveEvent<Boolean>()
    val downloading: LiveData<Boolean> get() = _downloading

    private val _cardClicked = SingleLiveEvent<Boolean>()
    val cardClicked: LiveData<Boolean> get() = _cardClicked

    private val _womenCategoryClicked = SingleLiveEvent<Boolean>()
    val womenCategoryClicked: LiveData<Boolean> get() = _womenCategoryClicked

    fun downloadCategories(){
        viewModelScope.launch(Dispatchers.IO){
            _downloading.postValue(true)
            _listOfCategories.postValue(dependencies.downloadCategories())
            _downloading.postValue(false)
        }
    }
    fun buttonWomenCategoryClicked(){
        _womenCategoryClicked.value = true
    }
    fun buttonClicked(){
        _cardClicked.value = true
    }
}