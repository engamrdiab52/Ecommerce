package com.example.bags.framework

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object LoginFlowViewModelFactory : ViewModelProvider.Factory {
    lateinit var application: Application
    lateinit var dependencies: Interactions

    fun inject(application: Application, dependencies: Interactions) {
        LoginFlowViewModelFactory.application = application
        LoginFlowViewModelFactory.dependencies = dependencies
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (BagsViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(
                Application::class.java,
                Interactions::class.java
            ).newInstance(application, dependencies)
        } else {
            throw IllegalStateException("viewModel must extend BagsViewModel")
        }
    }

}