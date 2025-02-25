package com.lukaslechner.coroutineusecasesonandroid.playground.structuredconcurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    val scope = CoroutineScope(Job())

    scope.launch {
        task()

        
    }


}

suspend fun task(){
    delay(100)
    println("hey from task")
}