package com.example.offerwalltest.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ParamsInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder()
            .build()

        val request = chain.request().newBuilder().apply {
            url(url)
            header("Content-Type", "application/json")

        }.build()

        return chain.proceed(request)
    }
}