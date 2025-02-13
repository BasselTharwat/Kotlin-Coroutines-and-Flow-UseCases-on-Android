package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase4

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.AndroidVersion
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class VariableAmountOfNetworkRequestsViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequestsSequentially() {
        uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                val recentVersions = mockApi.getRecentAndroidVersions()
                    .map { androidVersion ->
                        mockApi.getAndroidVersionFeatures(androidVersion.apiLevel)
                    }
                uiState.value = UiState.Success(recentVersions)
            }catch(exception: Exception){
                uiState.value = UiState.Error("Network request failed!")
            }
        }
    }

    fun performNetworkRequestsConcurrently() {
        uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                val recentVersions = mockApi.getRecentAndroidVersions()
                val versionFeatures = recentVersions.map {
                    async {
                        mockApi.getAndroidVersionFeatures(it.apiLevel)
                    }
                }.awaitAll()
                uiState.value = UiState.Success(versionFeatures)


            }catch(exception: Exception){
                uiState.value = UiState.Error("Network request failed!")
            }
        }

    }
}