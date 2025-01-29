package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase2.callbacks

import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.AndroidVersion
import com.lukaslechner.coroutineusecasesonandroid.mock.VersionFeatures
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SequentialNetworkRequestsCallbacksViewModel(
    private val mockApi: CallbackMockApi = mockApi()
) : BaseViewModel<UiState>() {

    private var getAndroidVersionsCall: Call<List<AndroidVersion>>? = null
    private var featuresOfMostRecentVersionCall: Call<VersionFeatures>? = null

    fun perform2SequentialNetworkRequest() {
        uiState.value = UiState.Loading

        getAndroidVersionsCall = mockApi.getRecentAndroidVersions()
        getAndroidVersionsCall!!.enqueue(object : Callback<List<AndroidVersion>> {
            override fun onResponse(
                p0: Call<List<AndroidVersion>>,
                p1: Response<List<AndroidVersion>>
            ) {
                if (p1.isSuccessful) {
                    val mostRecentVersion = p1.body()!!.last()
                    featuresOfMostRecentVersionCall =
                        mockApi.getAndroidVersionFeatures(mostRecentVersion.apiLevel)
                    featuresOfMostRecentVersionCall!!.enqueue(object : Callback<VersionFeatures> {
                        override fun onResponse(
                            p0: Call<VersionFeatures>,
                            p1: Response<VersionFeatures>
                        ) {
                            if (p1.isSuccessful) {
                                uiState.value = UiState.Success(p1.body()!!)
                            } else {
                                uiState.value = UiState.Error("Network request failed")
                            }
                        }

                        override fun onFailure(p0: Call<VersionFeatures>, p1: Throwable) {
                            uiState.value = UiState.Error("Something unexpected happened!")
                        }
                    })
                } else {
                    uiState.value = UiState.Error("Network request failed")
                }
            }

            override fun onFailure(p0: Call<List<AndroidVersion>>, p1: Throwable) {
                uiState.value = UiState.Error("Something unexpected happened!")
            }
        })
    }

    override fun onCleared() {
        super.onCleared()

        getAndroidVersionsCall?.cancel()
        featuresOfMostRecentVersionCall?.cancel()


    }
}
