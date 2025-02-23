package com.lukaslechner.coroutineusecasesonandroid.playground.structuredconcurrency

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

fun main(){

    val exceptionHandler = CoroutineExceptionHandler{ coroutineContext, throwable ->
        println("Caught exception $throwable")
    }

    //val scope = CoroutineScope(Job() + exceptionHandler)
    val scope = CoroutineScope(SupervisorJob()+ exceptionHandler)

    scope.launch {
        println("coroutine 1 starts")
        delay(50)
        println("coroutine 1 fails")
        throw RuntimeException()
    }

    scope.launch {
        println("coroutine 2 starts")
        delay(500)
        println("coroutine 2 completes")
    }.invokeOnCompletion { throwable ->
        if(throwable is CancellationException){
            println("coroutine 2 was cancelled")
        }

    }

    Thread.sleep(1000)

    println("scope got cancelled: ${!scope.isActive}")
}