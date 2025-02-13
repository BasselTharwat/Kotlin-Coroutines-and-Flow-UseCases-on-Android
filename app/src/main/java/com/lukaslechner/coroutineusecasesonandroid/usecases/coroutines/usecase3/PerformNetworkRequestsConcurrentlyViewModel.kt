package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase3

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class PerformNetworkRequestsConcurrentlyViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequestsSequentially() {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            try{
                val oreo = mockApi.getAndroidVersionFeatures(27)
                val pie = mockApi.getAndroidVersionFeatures(28)
                val android10 = mockApi.getAndroidVersionFeatures(29)
                uiState.value = UiState.Success(listOf(oreo, pie, android10))
            }catch (exception: Exception){
                uiState.value = UiState.Error("Network request failed!")
            }
        }
    }

    fun performNetworkRequestsConcurrently() {
        uiState.value = UiState.Loading

        try {
            val oreoDeferred = viewModelScope.async {
                mockApi.getAndroidVersionFeatures(27)
            }
            val pieDeferred = viewModelScope.async {
                mockApi.getAndroidVersionFeatures(28)
            }
            val android10Deferred = viewModelScope.async {
                mockApi.getAndroidVersionFeatures(29)
            }
            viewModelScope.launch {
                uiState.value = UiState.Success(awaitAll(oreoDeferred, pieDeferred, android10Deferred))
            }
        }catch(exception: Exception) {
            uiState.value = UiState.Error("Network request failed!")
        }




    }
}