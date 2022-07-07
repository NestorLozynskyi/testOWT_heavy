package com.example.offerwalltest.network.retrofit

import com.example.offerwalltest.data.Constants
import com.example.offerwalltest.network.interceptor.ParamsInterceptor
import com.example.offerwalltest.network.interceptor.ResponseInterceptor
import com.google.gson.Gson
//import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactoryImplement(private val paramsInterceptor: ParamsInterceptor,
                               private val responseInterceptor: ResponseInterceptor,
                               //private val chuckInterceptor: ChuckInterceptor
) : RetrofitFactory {

    override fun createRetrofit(gson: Gson): Retrofit {
        val okHttpBuilder =
            OkHttpClient().newBuilder()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.addInterceptor(loggingInterceptor)
        //okHttpBuilder.addInterceptor(chuckInterceptor)
        okHttpBuilder.addInterceptor(paramsInterceptor)
            .addInterceptor(responseInterceptor)
            .connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)

        val builder = Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpBuilder.build())

        return builder.build()
    }
}