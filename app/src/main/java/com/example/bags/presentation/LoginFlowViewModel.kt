package com.example.bags.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bags.framework.BagsViewModel
import com.example.bags.framework.Interactions
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFlowViewModel(application: Application, dependencies: Interactions) :
    BagsViewModel(application, dependencies) {
    private val _firebaseUser = MutableLiveData<FirebaseUser?>()
    val firebaseUser: LiveData<FirebaseUser?> get() = _firebaseUser

    fun signIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dependencies.signInUser(email, password)
        }
    }
    fun createUser(email: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            dependencies.signUpUser(email, password)
        }
    }
    fun signOut(){
        viewModelScope.launch(Dispatchers.IO){
            dependencies.signOutUser()
        }
    }
    fun resetPassword(email: String){
        viewModelScope.launch(Dispatchers.IO){
            dependencies.resetUserPassword(email)
        }
    }

}