package com.abhat.nasa_pictures_app.pictureslist.repository.fakes

import com.abhat.nasa_pictures_app.pictureslist.network.api.NasaApi
import com.abhat.nasa_pictures_app.pictureslist.network.model.NasaPictures
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

/**
 * Created by Anirudh Uppunda on 25,November,2020
 */
class FakeNasaApi(private val response: List<NasaPictures>): NasaApi {
    override suspend fun getNasaPictures(): Deferred<List<NasaPictures>> {
        return CompletableDeferred(response)
    }
}