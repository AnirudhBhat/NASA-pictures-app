package com.abhat.nasa_pictures_app.pictureslist.repository.state

import com.abhat.nasa_pictures_app.pictureslist.network.model.NasaPictures

/**
 * Created by Anirudh Uppunda on 25,November,2020
 */
sealed class NasaPicturesRepoState {
    data class Success(val response: List<NasaPictures>): NasaPicturesRepoState()
    data class Error(val error: Throwable): NasaPicturesRepoState()
}