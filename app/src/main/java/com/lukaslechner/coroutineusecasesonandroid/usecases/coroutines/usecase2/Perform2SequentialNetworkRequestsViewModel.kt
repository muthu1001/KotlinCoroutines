package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase2

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.launch
import java.lang.Exception

class Perform2SequentialNetworkRequestsViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun perform2SequentialNetworkRequest() {
        uiState.postValue(UiState.Loading)
        viewModelScope.launch {
            try {
                val androidVersion = mockApi.getRecentAndroidVersions()
                val latestFeatures =
                    mockApi.getAndroidVersionFeatures(androidVersion.last().apiLevel)
                uiState.postValue(UiState.Success(latestFeatures))
            } catch (ex: Exception) {
                uiState.postValue(UiState.Error("Network error in fetching android features"))
            }
        }
    }
}