package com.lukaslechner.coroutineusecasesonandroid.playground.flow.exceptionHandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    launch {
        stockFlow()
            .catch { cause: Throwable ->
                println("Caught $cause")
            }
            .collect {
                println("Collected $it")
            }
    }
}

private fun stockFlow(): Flow<String> = flow {
    repeat(5){ index ->
        delay(5000)
        if(index < 4){
            emit("New Stock Data")
        }else{
            throw Exception("Something went wrong")
        }
    }
}.retry(retries = 2) { cause: Throwable ->
    println("Caught $cause")
    cause is Exception
}