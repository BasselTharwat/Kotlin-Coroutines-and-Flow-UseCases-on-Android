package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase10

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger
import kotlin.system.measureTimeMillis

class CalculationInBackgroundViewModel : BaseViewModel<UiState>() {

    fun performCalculation(factorialOf: Int) {
        uiState.value = UiState.Loading
        var result = BigInteger.ONE
        var resultString = ""
        viewModelScope.launch{
            val computationDuration = measureTimeMillis {
                result = calculateFactorialOf(factorialOf)
            }
            val stringConversionDuration = measureTimeMillis {
                withContext(Dispatchers.Default){
                    resultString = result.toString()
                }
            }
            uiState.value = UiState.Success(resultString, computationDuration, stringConversionDuration)
        }


    }
    private suspend fun calculateFactorialOf(number: Int): BigInteger{
        return withContext(Dispatchers.Default){
            var factorial = BigInteger.ONE
            for (i in 1..number){
                factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
            }
            factorial
        }
    }
}