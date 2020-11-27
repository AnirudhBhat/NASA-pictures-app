package com.abhat.nasa_pictures_app.pictureslist.viewmodel.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Anirudh Uppunda on 26,November,2020
 */
@Parcelize
data class NasaPicturesViewModelModel(
    val date: String,
    val explaination: String,
    val title: String,
    val url: String
): Parcelable