package com.abhat.nasa_pictures_app.pictureslist.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.abhat.nasa_pictures_app.pictureslist.repository.fakes.FakeNasaApi
import com.abhat.nasa_pictures_app.pictureslist.repository.fakes.FakeNasaApiWhichThrowsError
import com.abhat.nasa_pictures_app.pictureslist.repository.fakes.FakeNasaPicturesResponse
import com.abhat.nasa_pictures_app.pictureslist.repository.state.NasaPicturesRepoState
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

/**
 * Created by Anirudh Uppunda on 25,November,2020
 */
class NasaPicturesRepositoryTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `calling nasa pictures repository should return proper state on success response`() {
        runBlocking {
            // Given
            val response = FakeNasaPicturesResponse.getNasaPictures()
            val nasaApi = FakeNasaApi(response)
            val nasaPicturesRepository = NasaPicturesRepositoryImpl(nasaApi)
            val expectedState = NasaPicturesRepoState.Success(response = FakeNasaPicturesResponse.getNasaPictures())

            // When
            val actualState = nasaPicturesRepository.getNasaPictures()

            // Then
            Assert.assertEquals(expectedState, actualState)
        }
    }

    @Test
    fun `calling nasa pictures repository should return proper state on error response`() {
        runBlocking {
            // Given
            val response = RuntimeException("error")
            val nasaApi = FakeNasaApiWhichThrowsError(response)
            val nasaPicturesRepository = NasaPicturesRepositoryImpl(nasaApi)
            val expectedState = NasaPicturesRepoState.Error(Throwable(response.message))

            // When
            val actualState = nasaPicturesRepository.getNasaPictures()

            // Then
            Assert.assertTrue(actualState is NasaPicturesRepoState.Error)
            Assert.assertEquals(expectedState.error.message, (actualState as NasaPicturesRepoState.Error).error.message)
        }
    }
}