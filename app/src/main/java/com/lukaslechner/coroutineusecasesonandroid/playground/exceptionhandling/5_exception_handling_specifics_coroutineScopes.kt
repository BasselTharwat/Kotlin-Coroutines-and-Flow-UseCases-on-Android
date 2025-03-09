package com.lukaslechner.coroutineusecasesonandroid.playground.exceptionhandling

import com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals.coroutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun main() {
    val scope = CoroutineScope(Job())

    try {
        scope.launch {
            throw RuntimeException()
        }
    } catch (e: Exception) {
        println("Coroutine failed")
    }
    try {
        coroutineScope {
            launch {
                throw RuntimeException()
            }
        }
    }catch (e: Exception){
        println("Coroutine failed")
    }
}