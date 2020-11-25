package com.abhat.nasa_pictures_app.pictureslist.repository

import com.abhat.nasa_pictures_app.pictureslist.repository.state.NasaPicturesRepoState

/**
 * Created by Anirudh Uppunda on 25,November,2020
 */
interface NasaPicturesRepository {
    suspend fun getNasaPictures(): NasaPicturesRepoState
}