package com.example.offerwalltest.ui.fragments.main

import androidx.lifecycle.MutableLiveData
import com.example.offerwalltest.base.BaseViewModel
import com.example.offerwalltest.data.ItemObj
import com.example.offerwalltest.data.response.IdsObj
import kotlinx.coroutines.Job

class MainViewModel(private val repository: MainRepository) : BaseViewModel() {

    val ldIds = MutableLiveData<IdsObj?>()
    val ldItem = MutableLiveData<ItemObj?>()

    private var idsJob: Job? = null
    private var itemJob: Job? = null

    init {
        getIds()
    }

    fun getIds(){
        if (idsJob != null) {
            cancel()
        }
        idsJob = launch({
            ldIds.postValue(repository.getIdsList())
        },{})
    }

    fun getItem(i: Int){
        if (itemJob != null || idsJob != null) {
            cancel()
        }
        itemJob = launch({
            ldItem.postValue(repository.getItem(i))
        },{})
    }

    private fun cancel(){
        idsJob?.cancel()
        itemJob?.cancel()
    }
}