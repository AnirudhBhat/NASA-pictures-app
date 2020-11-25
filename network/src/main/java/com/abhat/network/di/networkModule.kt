package com.abhat.network.di

import com.abhat.network.NetworkConstants
import com.abhat.network.coroutinehelpers.CoroutineContextProvider
import com.abhat.network.interceptors.MockInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Anirudh Uppunda on 19,October,2020
 */

val networkModule = module {
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideMockInterceptor() }
    single { provideCoroutineContextProvider() }
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

    return Retrofit.Builder()
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(NetworkConstants.BASE_URL)
        .client(okHttpClient)
        .build()
}

private fun provideOkHttpClient(interceptor: MockInterceptor): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(interceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
}

private fun provideMockInterceptor() = MockInterceptor()

private fun provideCoroutineContextProvider() = CoroutineContextProvider()