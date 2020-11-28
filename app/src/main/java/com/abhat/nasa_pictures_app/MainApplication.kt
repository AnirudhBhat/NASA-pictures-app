package com.abhat.nasa_pictures_app

import android.app.Application
import com.abhat.nasa_pictures_app.di.appModule
import com.abhat.network.di.networkModule
import org.koin.android.ext.android.startKoin

/**
 * Created by Anirudh Uppunda on 27,November,2020
 */
class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(networkModule, appModule))
        ConnectionMonitorLiveData.init(this)
    }
}