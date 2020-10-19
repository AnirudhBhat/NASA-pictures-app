package com.abhat.network.di

import com.abhat.network.NetworkConstants
import com.abhat.network.coroutinehelpers.CoroutineContextProvider
import com.abhat.network.interceptors.HostSelectionInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Anirudh Uppunda on 19,October,2020
 */

val networkModule = module {
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideCoroutineContextProvider() }
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val moshi = Moshi.Builder()
        .build()

    return Retrofit.Builder()
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(NetworkConstants.BASE_URL)
        .client(okHttpClient)
        .build()
}

private fun provideOkHttpClient(interceptor: HostSelectionInterceptor): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(interceptor)
        .hostnameVerifier { hostname, session -> true }
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
}

private fun provideCoroutineContextProvider() = CoroutineContextProvider()