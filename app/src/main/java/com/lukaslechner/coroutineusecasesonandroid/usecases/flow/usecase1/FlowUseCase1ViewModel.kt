package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.usecase1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FlowUseCase1ViewModel(
    stockPriceDataSource: StockPriceDataSource
) : BaseViewModel<UiState>() {

    val currentStockPriceAsLiveData: LiveData<UiState> =
        stockPriceDataSource
            .latestStockList // Flow<List<Stock>>
            .map { stockList -> UiState.Success(stockList) as UiState } // Flow<UiState>
            //map converts each List<Stock> into UiState.Success(stockList).
            .onStart { emit(UiState.Loading) } // Emits Loading at the beginning
            .asLiveData()
            //This starts collecting the flow inside viewModelScope,
            // and emits the result to the LiveData.

}