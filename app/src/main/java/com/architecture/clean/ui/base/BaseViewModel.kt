package com.architecture.clean.ui.base


import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel
    : ViewModel(), LifecycleObserver {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

