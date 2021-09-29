package com.amrabdelhamiddiab.bags.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.amrabdelhamiddiab.bags.framework.BagsViewModel
import com.amrabdelhamiddiab.bags.framework.Interactions
import com.amrabdelhamiddiab.bags.framework.utilis.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFlowViewModel(application: Application, dependencies: Interactions) :
    BagsViewModel(application, dependencies) {

    private val _passwordChanged = SingleLiveEvent<Boolean?>()
    val passwordChanged: LiveData<Boolean?> get() = _passwordChanged

    private val _emailVerificationSent = SingleLiveEvent<Boolean?>()
    val emailVerificationSent: LiveData<Boolean?> get() = _emailVerificationSent

    private val _userCreated = SingleLiveEvent<Boolean?>()
    val userCreated: LiveData<Boolean?> get() = _userCreated

    private val _userSignedIn = SingleLiveEvent<Boolean?>()
    val userSignedIn: LiveData<Boolean?> get() = _userSignedIn

    private val _emailVerified = SingleLiveEvent<Boolean?>()
    val emailVerified: LiveData<Boolean?> get() = _emailVerified

    private val _downloading = SingleLiveEvent<Boolean>()
    val downloading: LiveData<Boolean> get() = _downloading

    fun signIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _downloading.postValue(true)
            _userSignedIn.postValue(dependencies.signInUser(email, password))
           _downloading.postValue(false)
        }
    }

    fun createUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _downloading.postValue(true)
            _userCreated.postValue(dependencies.signUpUser(email, password))
            _downloading.postValue(false)
        }
    }


    fun resetPassword(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _downloading.postValue(true)
            _passwordChanged.postValue(dependencies.resetUserPassword(email))
            _downloading.postValue(false)
        }
    }

    fun sendVerificationEmail() {
        viewModelScope.launch(Dispatchers.IO) {
            _emailVerificationSent.postValue(dependencies.sendEmailVerification())
        }
    }

    fun isEmailVerified() {
        viewModelScope.launch(Dispatchers.IO) {
            _emailVerified.postValue(dependencies.emailVerifiedState())
        }
    }

}