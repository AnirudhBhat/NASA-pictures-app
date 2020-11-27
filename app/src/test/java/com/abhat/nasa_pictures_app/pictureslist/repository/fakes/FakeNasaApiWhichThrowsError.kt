package com.abhat.nasa_pictures_app.pictureslist.repository.fakes

import com.abhat.nasa_pictures_app.pictureslist.network.api.NasaApi
import com.abhat.nasa_pictures_app.pictureslist.network.model.NasaPictures
import kotlinx.coroutines.Deferred

/**
 * Created by Anirudh Uppunda on 25,November,2020
 */
class FakeNasaApiWhichThrowsError(private val error: Exception): NasaApi {
    override fun getNasaPictures(): Deferred<List<NasaPictures>> {
        throw error
    }
}