package com.abhat.nasa_pictures_app.pictureslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhat.nasa_pictures_app.pictureslist.NasaPicturesListUIState
import com.abhat.nasa_pictures_app.pictureslist.network.model.NasaPictures
import com.abhat.nasa_pictures_app.pictureslist.repository.NasaPicturesRepository
import com.abhat.nasa_pictures_app.pictureslist.repository.state.NasaPicturesRepoState
import com.abhat.nasa_pictures_app.pictureslist.viewmodel.model.NasaPicturesViewModelModel
import com.abhat.nasa_pictures_app.pictureslist.viewmodel.state.NasaPicturesListViewModelState
import com.abhat.network.coroutinehelpers.CoroutineContextProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

/**
 * Created by Anirudh Uppunda on 26,November,2020
 */
class NasaPicturesListViewModel(
    private val nasaPicturesRepository: NasaPicturesRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {
    private val nasaPicturesListUIState = MutableLiveData<NasaPicturesListUIState>()
    fun getNasaPicturesListUIState() = nasaPicturesListUIState as LiveData<NasaPicturesListUIState>

    private var currentUiState = NasaPicturesListUIState()
        set(value) {
            field = value
            nasaPicturesListUIState.postValue(value)
        }

    fun onAction(action: Action) {
        when (action) {
            is Action.GetNasaPictures -> {
                reducer(NasaPicturesListViewModelState.IsLoading(true))
                getNasaPictures()
            }
        }
    }

    private fun getNasaPictures() {
        viewModelScope.launch(coroutineContextProvider.IO) {
            supervisorScope {
                val response = nasaPicturesRepository.getNasaPictures()
                when (response) {
                    is NasaPicturesRepoState.Success -> {
                        reducer(NasaPicturesListViewModelState.Success(mapper(response.response)))
                    }

                    is NasaPicturesRepoState.Error -> {
                        reducer(NasaPicturesListViewModelState.Error(response.error))
                    }
                }
            }
        }
    }

    private fun mapper(nasaRepoModel: List<NasaPictures>): List<NasaPicturesViewModelModel> {
        return nasaRepoModel.map {
            NasaPicturesViewModelModel(
                date = it.date,
                explaination = it.explaination,
                title = it.title,
                url = it.url
            )
        }
    }

    private fun reducer(nasaPicturesListViewModelState: NasaPicturesListViewModelState) {
        currentUiState = when (nasaPicturesListViewModelState) {
            is NasaPicturesListViewModelState.IsLoading -> {
                currentUiState.copy(isLoading = true)
            }

            is NasaPicturesListViewModelState.Success -> {
                currentUiState.copy(isLoading = false, picturesList = nasaPicturesListViewModelState.model, error = null)
            }

            is NasaPicturesListViewModelState.Error -> {
                currentUiState.copy(isLoading = false, picturesList = null, error = nasaPicturesListViewModelState.throwable)
            }
        }
    }

    sealed class Action {
        object GetNasaPictures: Action()
    }
}