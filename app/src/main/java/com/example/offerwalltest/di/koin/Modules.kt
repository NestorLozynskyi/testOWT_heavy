package com.example.offerwalltest.di.koin

import com.example.offerwalltest.api.MainApiInterface
import com.example.offerwalltest.network.interceptor.ParamsInterceptor
import com.example.offerwalltest.network.interceptor.ResponseInterceptor
import com.example.offerwalltest.network.retrofit.RetrofitFactory
import com.example.offerwalltest.network.retrofit.RetrofitFactoryImplement
import com.example.offerwalltest.ui.fragments.main.MainRepository
import com.example.offerwalltest.ui.fragments.main.MainViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
//import com.readystatesoftware.chuck.ChuckInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val retrofitModule = module(override = true) {

    //single { ChuckInterceptor(get()) }

    single { ParamsInterceptor() }
    single { ResponseInterceptor() }
    single<Gson> { GsonBuilder().setLenient().create() }
    single<RetrofitFactory> { RetrofitFactoryImplement(get(), get()) }
    single { get<RetrofitFactory>().createRetrofit(get()) }

    single { get<Retrofit>().create(MainApiInterface::class.java) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    factory { MainRepository(get()) }
}