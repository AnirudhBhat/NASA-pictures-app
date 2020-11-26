package com.abhat.nasa_pictures_app.pictureslist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.abhat.nasa_pictures_app.pictureslist.NasaPicturesListUIState
import com.abhat.nasa_pictures_app.pictureslist.repository.fakes.FakeNasaPicturesResponse
import com.abhat.nasa_pictures_app.pictureslist.viewmodel.fakes.FakeNasaPicturesListRepository
import com.abhat.nasa_pictures_app.pictureslist.viewmodel.fakes.FakeNasaPicturesListRepositoryWhichThrowsError
import com.abhat.network.coroutinehelpers.CoroutineContextProvider
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Anirudh Uppunda on 26,November,2020
 */
class NasaPicturesListViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var nasaPicturesUIStateObserver: Observer<NasaPicturesListUIState>

    @Before
    fun setup() {
        nasaPicturesUIStateObserver = mock()
    }


    class TestContextProvider : CoroutineContextProvider() {
        override val Main: CoroutineDispatcher = Dispatchers.Unconfined
        override val IO: CoroutineDispatcher = Dispatchers.Unconfined
    }

    @Test
    fun `getNasaPictures must show loading, return response and then hide loading on success`() {
        runBlocking {
            // Given
            val response = FakeNasaPicturesResponse.getNasaPictures()
            val viewmodelMappedResponse = FakeNasaPicturesResponse.getNasaPicturesViewModelModel()
            val repository = FakeNasaPicturesListRepository(response)
            val viewmodel = NasaPicturesListViewModel(repository, TestContextProvider())
            viewmodel.getNasaPicturesListUIState().observeForever(nasaPicturesUIStateObserver)
            val loadinsState = NasaPicturesListUIState(isLoading = true, picturesList = null, error = null)
            val successState = NasaPicturesListUIState(isLoading = false, picturesList = viewmodelMappedResponse, error = null)


            // When
            viewmodel.onAction(NasaPicturesListViewModel.Action.GetNasaPictures)

            // Then
            val inOrder = inOrder(nasaPicturesUIStateObserver)
            inOrder.verify(nasaPicturesUIStateObserver).onChanged(loadinsState)
            inOrder.verify(nasaPicturesUIStateObserver).onChanged(successState)
        }
    }

    @Test
    fun `getNasaPictures must show loading, return erro response and then hide loading on error`() {
        runBlocking {
            // Given
            val response = RuntimeException()
            val repository = FakeNasaPicturesListRepositoryWhichThrowsError(response)
            val viewmodel = NasaPicturesListViewModel(repository, TestContextProvider())
            viewmodel.getNasaPicturesListUIState().observeForever(nasaPicturesUIStateObserver)
            val loadinsState = NasaPicturesListUIState(isLoading = true, picturesList = null, error = null)
            val successState = NasaPicturesListUIState(isLoading = false, picturesList = null, error = response)


            // When
            viewmodel.onAction(NasaPicturesListViewModel.Action.GetNasaPictures)

            // Then
            val inOrder = inOrder(nasaPicturesUIStateObserver)
            inOrder.verify(nasaPicturesUIStateObserver).onChanged(loadinsState)
            inOrder.verify(nasaPicturesUIStateObserver).onChanged(successState)
        }
    }
}