package com.lukaslechner.coroutineusecasesonandroid.playground.flow.exceptionHandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    launch {
        val stocksFlow = stockFlow()
            .onCompletion { cause: Throwable? ->
                if (cause != null) {
                    println("Flow completed exceptionally $cause")
                } else {
                    println("Flow completed successfully")
                }

            }
            .catch { cause: Throwable ->
                println("Caught $cause")

            }
            .collect {
                println("Collected $it")
            }
    }




}

private fun stockFlow(): Flow<String> = flow {
    emit("Apple")
    emit("Microsoft")

    throw Exception("Something went wrong")
}