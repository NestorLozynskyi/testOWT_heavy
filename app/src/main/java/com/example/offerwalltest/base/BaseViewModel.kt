package com.example.offerwalltest.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.offerwalltest.utils.coroutine.CoroutineHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel: ViewModel() {

    private var coroutineHelper = CoroutineHelper(viewModelScope)

    protected open fun launch(
        block: suspend CoroutineScope.() -> Unit,
        onError: (e: Throwable) -> Unit,
        coroutineContext: CoroutineContext = Dispatchers.IO,
    ): Job {
        return coroutineHelper.launch(coroutineContext, block, onError)
    }
}