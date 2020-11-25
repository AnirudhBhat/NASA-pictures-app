package com.abhat.nasa_pictures_app.pictureslist.repository

import com.abhat.nasa_pictures_app.pictureslist.network.api.NasaApi
import com.abhat.nasa_pictures_app.pictureslist.repository.state.NasaPicturesRepoState

/**
 * Created by Anirudh Uppunda on 25,November,2020
 */
class NasaPicturesRepositoryImpl(private val nasaApi: NasaApi): NasaPicturesRepository {
    override suspend fun getNasaPictures(): NasaPicturesRepoState {
        return try {
            val nasaPictures = nasaApi.getNasaPictures().await()
            NasaPicturesRepoState.Success(response = nasaPictures)
        } catch (e: Exception) {
            NasaPicturesRepoState.Error(error = Throwable(e.message))
        }
    }
}