package com.abhat.nasa_pictures_app.pictureslist.viewmodel.state

import com.abhat.nasa_pictures_app.pictureslist.viewmodel.model.NasaPicturesViewModelModel

/**
 * Created by Anirudh Uppunda on 26,November,2020
 */
sealed class NasaPicturesListViewModelState {
    data class IsLoading(val isLoading: Boolean): NasaPicturesListViewModelState()
    data class Success(val model: List<NasaPicturesViewModelModel>): NasaPicturesListViewModelState()
    data class Error(val throwable: Throwable): NasaPicturesListViewModelState()
}