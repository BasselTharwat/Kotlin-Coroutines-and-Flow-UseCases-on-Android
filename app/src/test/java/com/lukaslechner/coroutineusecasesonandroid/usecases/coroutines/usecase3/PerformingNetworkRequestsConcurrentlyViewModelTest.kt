package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase3

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lukaslechner.coroutineusecasesonandroid.mock.mockVersionFeaturesAndroid10
import com.lukaslechner.coroutineusecasesonandroid.mock.mockVersionFeaturesOreo
import com.lukaslechner.coroutineusecasesonandroid.mock.mockVersionFeaturesPie
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase3.UiState
import com.lukaslechner.coroutineusecasesonandroid.utils.ReplaceMainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class PerformingNetworkRequestsConcurrentlyViewModelTest {

    private val receivedUiStates = mutableListOf<UiState>()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get: Rule
    val replaceMainDispatcherRule = ReplaceMainDispatcherRule()


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `performNetworkRequestsSequentially() should load data sequentially`() = runTest{
        // Arrange
        val responseDelay = 1000L
        val fakeApi = FakeSuccessApi(responseDelay)
        val viewModel = PerformNetworkRequestsConcurrentlyViewModel(
            mockApi = fakeApi
        )
        observeViewModel(viewModel)

        // Act
        val startTime = currentTime
        viewModel.performNetworkRequestsSequentially()
        advanceUntilIdle()
        val endTime = currentTime
        val totalTime = endTime - startTime

        // Assert
        Assert.assertEquals(
            listOf(
                UiState.Loading,
                UiState.Success(
                    listOf(
                        mockVersionFeaturesOreo,
                        mockVersionFeaturesPie,
                        mockVersionFeaturesAndroid10
                    )
                )
            ),
            receivedUiStates
        )

        Assert.assertEquals(3000, totalTime)

    }

    @Test
    fun `performNetworkRequestsConcurrently() should load data concurrently`() = runTest{
        // Arrange
        val responseDelay = 1000L
        val fakeApi = FakeSuccessApi(responseDelay)
        val viewModel = PerformNetworkRequestsConcurrentlyViewModel(
            mockApi = fakeApi
        )
        observeViewModel(viewModel)

        // Act
        val startTime = currentTime
        viewModel.performNetworkRequestsConcurrently()
        advanceUntilIdle()
        val endTime = currentTime
        val totalTime = endTime - startTime



        // Assert
        Assert.assertEquals(
            listOf(
                UiState.Loading,
                UiState.Success(
                    listOf(
                        mockVersionFeaturesOreo,
                        mockVersionFeaturesPie,
                        mockVersionFeaturesAndroid10
                    )
                )
            ),
            receivedUiStates
        )

        Assert.assertEquals(1000, totalTime)


    }

    private fun observeViewModel(viewModel: PerformNetworkRequestsConcurrentlyViewModel) {
        viewModel.uiState().observeForever { uiState ->
            if (uiState != null) {
                receivedUiStates.add(uiState)
            }
        }
    }
}