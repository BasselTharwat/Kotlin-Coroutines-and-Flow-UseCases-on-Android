package com.lukaslechner.coroutineusecasesonandroid.playground.flow.exceptionHandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {

    flow {
        emit(1)
        emit(2)
        emit(3)
    }.collect {
        println("Collected $it")
    }
}

val inlinedFlow = flow<Int> {
    println("Collected 1")
    println("Collected 2")
    println("Collected 3")
}