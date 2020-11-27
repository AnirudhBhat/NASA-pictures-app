package com.abhat.nasa_pictures_app.di

import com.abhat.nasa_pictures_app.pictureslist.network.api.NasaApi
import com.abhat.nasa_pictures_app.pictureslist.repository.NasaPicturesRepositoryImpl
import com.abhat.nasa_pictures_app.pictureslist.viewmodel.NasaPicturesListViewModel
import com.abhat.network.coroutinehelpers.CoroutineContextProvider
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

/**
 * Created by Anirudh Uppunda on 27,November,2020
 */

val appModule = module {
    viewModel { provideNasaPicturesListViewModel(get(), get()) }
    factory { provideNasaPicturesRepository(get()) }
    factory { provideNasaApi(get()) }
}

fun provideNasaPicturesListViewModel(nasaPicturesRepository: NasaPicturesRepositoryImpl, coroutineContextProvider: CoroutineContextProvider) = NasaPicturesListViewModel(nasaPicturesRepository, coroutineContextProvider)

fun provideNasaPicturesRepository(nasaApi: NasaApi) = NasaPicturesRepositoryImpl(nasaApi)

fun provideNasaApi(retrofit: Retrofit) = retrofit.create(NasaApi::class.java)