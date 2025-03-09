package com.lukaslechner.coroutineusecasesonandroid.playground.exceptionhandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

suspend fun main(){
    try {
        supervisorScope {
            launch {
                throw RuntimeException()
            }
        }
    } catch (e: Exception) {
        println("Coroutine failed")
    }
}