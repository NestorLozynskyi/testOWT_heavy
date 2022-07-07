package com.example.offerwalltest.api

import com.example.offerwalltest.data.ItemObj
import com.example.offerwalltest.data.response.IdsObj
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApiInterface {
    @GET("/api/v1/entities/getAllIds")
    suspend fun getIdsList(): IdsObj?

    @GET("/api/v1/object/{id}")
    suspend fun getItem(
        @Path("id") id: Int
    ): ItemObj?
}