package com.abhat.nasa_pictures_app.pictureslist.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Anirudh Uppunda on 25,November,2020
 */
data class NasaPictures(
    @SerializedName("copyright")
    private val copyright: String,
    @SerializedName("date")
    private val date: String,
    @SerializedName("explaination")
    private val explaination: String,
    @SerializedName("hdurl")
    private val hdurl: String,
    @SerializedName("media_type")
    private val mediaType: String,
    @SerializedName("service_version")
    private val serviceVersion: String,
    @SerializedName("title")
    private val title: String,
    @SerializedName("url")
    private val url: String

)