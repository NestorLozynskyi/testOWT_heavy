package com.example.offerwalltest.ui.fragments.main

import com.example.offerwalltest.api.MainApiInterface
import com.example.offerwalltest.data.ItemObj
import com.example.offerwalltest.data.response.IdsObj

class MainRepository(private val api: MainApiInterface) {

    suspend fun getIdsList(): IdsObj? = api.getIdsList()
    suspend fun getItem(i: Int): ItemObj? = api.getItem(i)
}