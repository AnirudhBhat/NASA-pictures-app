package com.abhat.nasa_pictures_app.pictureslist

import com.abhat.nasa_pictures_app.pictureslist.viewmodel.model.NasaPicturesViewModelModel

/**
 * Created by Anirudh Uppunda on 26,November,2020
 */
data class NasaPicturesListUIState(
    private val isLoading: Boolean = false,
    private val picturesList: List<NasaPicturesViewModelModel>? = null,
    private val error: Throwable? = null
)