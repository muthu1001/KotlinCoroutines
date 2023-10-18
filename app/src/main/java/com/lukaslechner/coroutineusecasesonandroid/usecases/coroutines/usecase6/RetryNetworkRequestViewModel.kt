package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase6

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.AndroidVersion
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import timber.log.Timber

class RetryNetworkRequestViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequest() {
        viewModelScope.launch {
            uiState.postValue(UiState.Loading)
            val result: List<AndroidVersion>? =
                retriesWithExponentialBackOff(5, ::getAndroidVersion)
            if (result != null) {
                uiState.postValue(UiState.Success(result))
            } else {
                uiState.postValue(UiState.Error("Network req failed"))
            }
        }
    }

    private suspend fun <T> retriesWithExponentialBackOff(
        retires: Int,
        block: suspend () -> T?,
        initialDelay: Long = 100L,
        maxDelay: Long = 1000L,
        delayMultiplier: Int = 2
    ): T? {
        var result: T? = null
        var delayTime = initialDelay
        repeat(retires - 1) {
            try {
                result = block()
                if (result != null) {
                    return@repeat
                }
            } catch (ex: Exception) {
                Timber.e(ex)
            }
            delay(delayTime)
            delayTime = getDelayTime(initialDelay, maxDelay, delayMultiplier * (it + 1))
        }
        return if (result != null)
            result
        else block()
    }

    private fun getDelayTime(initialDelay: Long, maxDelay: Long, delayMultiplier: Int): Long {
        val newDelay = initialDelay * delayMultiplier
        println("delay for next ${if (newDelay > maxDelay) maxDelay else newDelay}")
        return if (newDelay > maxDelay) maxDelay else newDelay
    }


    private suspend fun getAndroidVersion(timeOut: Long = 1100): List<AndroidVersion>? {
        return try {
            withTimeoutOrNull(timeOut) {
                api.getRecentAndroidVersions()
            }
        } catch (ex: Exception) {
            Timber.e(ex)
            null
        }
    }

}