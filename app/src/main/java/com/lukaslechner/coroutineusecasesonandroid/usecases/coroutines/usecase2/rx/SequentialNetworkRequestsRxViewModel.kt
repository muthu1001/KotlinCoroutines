package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase2.rx

import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SequentialNetworkRequestsRxViewModel(
    private val mockApi: RxMockApi = mockApi()
) : BaseViewModel<UiState>() {

    private val disposable = CompositeDisposable()

    fun perform2SequentialNetworkRequest() {
        uiState.postValue(UiState.Loading)
        mockApi.getRecentAndroidVersions().flatMap { androidVersions ->
            mockApi.getAndroidVersionFeatures(androidVersions.last().apiLevel)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onSuccess = {
                    uiState.postValue(UiState.Success(it))
                },
                onError = {
                    uiState.postValue(UiState.Error("Error in getting latest android features"))
                }
            ).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}