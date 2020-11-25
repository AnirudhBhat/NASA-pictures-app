package com.abhat.nasa_pictures_app.pictureslist.repository.fakes

import com.abhat.nasa_pictures_app.pictureslist.network.model.NasaPictures

/**
 * Created by Anirudh Uppunda on 25,November,2020
 */
object FakeNasaPicturesResponse {

    fun getNasaPictures(): List<NasaPictures> {
        return listOf(
            NasaPictures(
                copyright = "",
                date = "",
                explaination = "",
                hdurl = "",
                mediaType = "",
                serviceVersion = "",
                title = "",
                url = ""
            ),
            NasaPictures(
                copyright = "",
                date = "",
                explaination = "",
                hdurl = "",
                mediaType = "",
                serviceVersion = "",
                title = "",
                url = ""
            ),
            NasaPictures(
                copyright = "",
                date = "",
                explaination = "",
                hdurl = "",
                mediaType = "",
                serviceVersion = "",
                title = "",
                url = ""
            )
        )
    }
}