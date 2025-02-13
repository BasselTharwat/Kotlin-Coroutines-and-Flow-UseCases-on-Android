package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase7

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import timber.log.Timber

class TimeoutAndRetryViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequest() {
        uiState.value = UiState.Loading
        val numberOfRetries = 2
        val timeout = 1000L

        viewModelScope.launch {
            try{
                val oreoDeferred = async {
                    retry(numberOfRetries){
                        withTimeout(timeout) {
                            api.getAndroidVersionFeatures(27)
                        }
                    }

                }
                val pieDeferred = async {
                    retry(numberOfRetries) {
                        withTimeout(timeout) {
                            api.getAndroidVersionFeatures(28)
                        }
                    }
                }
                val oreoFeatures = oreoDeferred.await()
                val pieFeatures = pieDeferred.await()
                uiState.value = UiState.Success(listOf(oreoFeatures, pieFeatures))
            }catch (exception: Exception){
                uiState.value = UiState.Error("Network request failed!")
            }


        }
    }


    private suspend fun <T> retry(numOfRetries: Int,
                                  initialDelayMillis: Long = 100,
                                  maxDelayMillis: Long = 1000,
                                  factor: Double = 2.0,
                                  block: suspend () -> T): T{
        var currentDelay = initialDelayMillis
        repeat(numOfRetries){
            try{
                return block()
            }catch(exception: Exception) {
                Timber.e(exception)
            }
            delay(currentDelay)
            currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelayMillis)
        }
        return block()
    }
}