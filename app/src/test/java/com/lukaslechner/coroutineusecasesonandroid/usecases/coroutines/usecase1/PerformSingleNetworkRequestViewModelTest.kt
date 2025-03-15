package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase1

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lukaslechner.coroutineusecasesonandroid.mock.mockAndroidVersions
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase1.UiState
import com.lukaslechner.coroutineusecasesonandroid.utils.ReplaceMainDispatcherRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class PerformSingleNetworkRequestViewModelTest {

    private val receivedUiStates = mutableListOf<UiState>()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get: Rule
    val replaceMainDispatcherRule = ReplaceMainDispatcherRule()

    @Test
    fun `should return Success when network request is successful`() {

        // Arrange
        val fakeApi = FakeSuccessApi()
        val viewModel = PerformSingleNetworkRequestViewModel(
            mockApi = fakeApi
        )
        observeViewModel(viewModel)

        assertTrue(receivedUiStates.isEmpty())

        // Act
        viewModel.performSingleNetworkRequest()

        // Assert
        assertEquals(
            listOf(
                UiState.Loading,
                UiState.Success(mockAndroidVersions)
            ),
            receivedUiStates
        )


    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should return error when network request fails`(){
    // Arrange
        replaceMainDispatcherRule
        val fakeApi = FakeErrorApi()
        val viewModel = PerformSingleNetworkRequestViewModel(
            mockApi = fakeApi
        )
        observeViewModel(viewModel)

    // Act
        viewModel.performSingleNetworkRequest()

    // Assert
        assertEquals(
            listOf(
                UiState.Loading,
                UiState.Error("Network error")
            ),
            receivedUiStates
        )



    }

    private fun observeViewModel(viewModel: PerformSingleNetworkRequestViewModel) {
        viewModel.uiState().observeForever { uiState ->
            if (uiState != null) {
                receivedUiStates.add(uiState)
            }
        }
    }
}