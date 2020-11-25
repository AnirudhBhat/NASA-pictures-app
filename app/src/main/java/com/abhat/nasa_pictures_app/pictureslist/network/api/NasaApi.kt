package com.abhat.nasa_pictures_app.pictureslist.network.api

import com.abhat.nasa_pictures_app.pictureslist.network.model.NasaPictures
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 * Created by Anirudh Uppunda on 25,November,2020
 */
interface NasaApi {
    @GET("pictures")
    suspend fun getNasaPictures(): Deferred<List<NasaPictures>>
}