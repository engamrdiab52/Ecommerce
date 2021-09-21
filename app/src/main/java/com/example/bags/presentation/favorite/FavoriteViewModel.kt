package com.example.bags.presentation.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bags.framework.BagsViewModel
import com.example.bags.framework.Interactions
import com.example.bags.framework.LoginFlowViewModelFactory.application
import com.example.bags.framework.LoginFlowViewModelFactory.dependencies
import com.example.bags.framework.utilis.SingleLiveEvent
import com.example.core.domain.FavoriteOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application, dependencies: Interactions) :
    BagsViewModel(application, dependencies) {
    private val _listOfFavorites = MutableLiveData<List<FavoriteOrder>>()
    val listOfFavorites: LiveData<List<FavoriteOrder>> get() = _listOfFavorites

    private val _downloading = SingleLiveEvent<Boolean>()
    val downloading: LiveData<Boolean> get() = _downloading

    fun downloadFavorites(){
        viewModelScope.launch(Dispatchers.IO){
            _downloading.postValue(true)
            _listOfFavorites.postValue(dependencies.downloadFavorites())
            _downloading.postValue(false)
        }
    }
}