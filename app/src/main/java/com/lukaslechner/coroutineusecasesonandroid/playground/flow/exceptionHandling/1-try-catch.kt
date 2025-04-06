package com.lukaslechner.coroutineusecasesonandroid.playground.flow.exceptionHandling


import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    launch{
        try {
            val stocksFlow = stockFlow()

            stocksFlow
                .map {
                    throw Exception("Something went wrong 2")
                }
                .onCompletion { cause: Throwable? ->
                    if (cause != null) {
                        println("Flow completed exceptionally $cause")
                    } else {
                        println("Flow completed successfully")
                    }

                }

                .collect{
                    println("Collected $it")
                }
        } catch (e: Exception) {
            println("Caught $e")
        }


    }




}

private fun stockFlow(): Flow<String> = flow {
    emit("Apple")
    emit("Microsoft")

    throw Exception("Something went wrong")
}