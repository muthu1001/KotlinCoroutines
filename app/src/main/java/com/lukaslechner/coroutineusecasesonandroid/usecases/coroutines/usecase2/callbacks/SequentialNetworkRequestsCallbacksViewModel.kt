package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase2.callbacks

import android.annotation.SuppressLint
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.AndroidVersion
import com.lukaslechner.coroutineusecasesonandroid.mock.VersionFeatures
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SequentialNetworkRequestsCallbacksViewModel(
    private val mockApi: CallbackMockApi = mockApi()
) : BaseViewModel<UiState>() {

    private var androidVersionCall : Call<List<AndroidVersion>>? = null
    private var androidFeaturesCall : Call<VersionFeatures>? = null

    @SuppressLint("SuspiciousIndentation")
    fun perform2SequentialNetworkRequest() {
        uiState.postValue(UiState.Loading)
        androidVersionCall = mockApi.getRecentAndroidVersions()
            androidVersionCall?.enqueue(object : Callback<List<AndroidVersion>>{
            override fun onResponse(
                call: Call<List<AndroidVersion>>,
                response: Response<List<AndroidVersion>>
            ) {
                if(response.isSuccessful && response.body()!=null){
                    val recentAndroidVersion = response.body()!!.last()
                    androidFeaturesCall = mockApi.getAndroidVersionFeatures(recentAndroidVersion.apiLevel)
                        androidFeaturesCall?.enqueue(object : Callback<VersionFeatures>{
                        override fun onResponse(
                            call: Call<VersionFeatures>,
                            response: Response<VersionFeatures>
                        ) {
                            if(response.isSuccessful && response.body()!=null){
                                uiState.postValue(UiState.Success(response.body()!!))
                            }else uiState.postValue(UiState.Error("Network error in getting recent version features"))
                        }

                        override fun onFailure(call: Call<VersionFeatures>, t: Throwable) {
                            uiState.postValue(UiState.Error("Failure in getting recent version features"))
                        }

                    })
                }else uiState.postValue(UiState.Error("Network error in getting recent android version"))
            }

            override fun onFailure(call: Call<List<AndroidVersion>>, t: Throwable) {
                uiState.postValue(UiState.Error("Failure in getting recent android version"))
            }

        })
    }

    override fun onCleared() {
        super.onCleared()
        androidVersionCall?.cancel()
        androidFeaturesCall?.cancel()
    }
}