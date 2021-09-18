package com.example.bags.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bags.framework.BagsViewModel
import com.example.bags.framework.Interactions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFlowViewModel(application: Application, dependencies: Interactions) :
    BagsViewModel(application, dependencies) {
    private val _passwordChanged = MutableLiveData<Boolean>()
    val passwordChanged: LiveData<Boolean> get() = _passwordChanged

    private val _emailVerificationSent = MutableLiveData<Boolean>()
    val emailVerificationSent: LiveData<Boolean> get() = _emailVerificationSent

    private val _userCreated = MutableLiveData<Boolean>()
    val userCreated: LiveData<Boolean> get() = _userCreated

    private val _userSignedIn = MutableLiveData<Boolean>()
    val userSignedIn: LiveData<Boolean> get() = _userSignedIn

    private val _emailVerified = MutableLiveData<Boolean>()
    val emailVerified: LiveData<Boolean> get() = _emailVerified
    
    fun signIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
        _userSignedIn.postValue( dependencies.signInUser(email, password))
            
        /*
            if (FirebaseAuth.getInstance().currentUser?.isEmailVerified == true) {
            //    dependencies.userLoggedIn
                Log.d(TAG, " email verified  ")
            } else {
                Log.d(TAG, "please verify email")
            }*/
        }
    }

    fun createUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
          _userCreated.postValue(dependencies.signUpUser(email, password))
        }
    }

    fun signOut() {
        viewModelScope.launch(Dispatchers.IO) {
            dependencies.signOutUser()
        }
    }

    fun resetPassword(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
           _passwordChanged.postValue(dependencies.resetUserPassword(email))
        }
    }
    fun sendVerificationEmail(){
        viewModelScope.launch(Dispatchers.IO){
            _emailVerificationSent.postValue(dependencies.sendEmailVerification())
        }
        fun isEmailVerified(){
            viewModelScope.launch(Dispatchers.IO){
                _emailVerified.postValue(dependencies.)
            }
        }
    }

}