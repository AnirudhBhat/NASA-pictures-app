package com.abhat.nasa_pictures_app.pictureslist.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Anirudh Uppunda on 25,November,2020
 */
data class NasaPictures(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("explaination")
    val explaination: String,
    @SerializedName("hdurl")
    val hdurl: String,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("service_version")
    val serviceVersion: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String

)