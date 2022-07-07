package com.example.offerwalltest.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LifecycleOwner.observeLiveData(liveData: LiveData<T>?, action: (T) -> Unit) {
    liveData?.observe(this, Observer { value ->
        value?.let{ action.invoke(it) }
    })
}