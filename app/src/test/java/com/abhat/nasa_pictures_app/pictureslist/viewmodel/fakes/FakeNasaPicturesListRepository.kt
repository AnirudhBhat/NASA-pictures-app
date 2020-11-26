package com.abhat.nasa_pictures_app.pictureslist.viewmodel.fakes

import com.abhat.nasa_pictures_app.pictureslist.network.model.NasaPictures
import com.abhat.nasa_pictures_app.pictureslist.repository.NasaPicturesRepository
import com.abhat.nasa_pictures_app.pictureslist.repository.state.NasaPicturesRepoState

/**
 * Created by Anirudh Uppunda on 26,November,2020
 */
class FakeNasaPicturesListRepository(private val response: List<NasaPictures>): NasaPicturesRepository {
    override suspend fun getNasaPictures(): NasaPicturesRepoState {
        return NasaPicturesRepoState.Success(response)
    }
}