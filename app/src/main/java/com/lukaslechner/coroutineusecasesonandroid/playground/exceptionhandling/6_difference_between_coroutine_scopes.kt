package com.lukaslechner.coroutineusecasesonandroid.playground.exceptionhandling

import kotlinx.coroutines.*


fun main() {
    val scope = CoroutineScope(Job()) // Creates an independent coroutine scope

    scope.launch {
        println("Coroutine 1 started")
        delay(1000)
        println("Coroutine 1 finished")
    }

    scope.launch {
        println("Coroutine 2 started")
        delay(500)
        throw RuntimeException("Coroutine 2 failed!") // This fails but does not cancel Coroutine 1
    }

    Thread.sleep(2000) // Keeps main alive long enough to see results
}
