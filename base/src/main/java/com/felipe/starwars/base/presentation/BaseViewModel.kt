package com.felipe.starwars.base.presentation

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel(), DefaultLifecycleObserver {

    override fun onCleared() {
        super.onCleared()
    }
}
