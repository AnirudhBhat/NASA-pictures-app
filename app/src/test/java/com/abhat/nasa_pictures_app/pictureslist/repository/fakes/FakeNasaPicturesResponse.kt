package com.abhat.nasa_pictures_app.pictureslist.repository.fakes

import com.abhat.nasa_pictures_app.pictureslist.network.model.NasaPictures
import com.abhat.nasa_pictures_app.pictureslist.viewmodel.model.NasaPicturesViewModelModel

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

    fun getNasaPicturesViewModelModel(): List<NasaPicturesViewModelModel> {
        return getNasaPictures().map {
            NasaPicturesViewModelModel(
                date = it.date,
                explaination = it.explaination,
                title = it.title,
                url = it.url
            )
        }
    }
}