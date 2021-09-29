package com.amrabdelhamiddiab.bags.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel

open class BagsViewModel(application: Application, protected val dependencies: Interactions) :
    AndroidViewModel(application) {
    protected val application: BagsApplication = this.getApplication()
}