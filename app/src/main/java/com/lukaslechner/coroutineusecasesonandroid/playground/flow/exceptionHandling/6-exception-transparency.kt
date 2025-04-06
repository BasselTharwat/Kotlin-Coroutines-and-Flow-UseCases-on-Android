package com.lukaslechner.coroutineusecasesonandroid.playground.flow.exceptionHandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {

    flow {
        try {
            emit(1)
        }catch (e: Exception){
            println("Caught $e")
        }
    }
        .collect{
            throw Exception("Something went wrong")
        }
}
