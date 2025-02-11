package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main(){
    println("main starts on ${Thread.currentThread().name}")
    runBlocking {
        launch {  multiThreadedCoroutine(1, 500L) }
        launch { multiThreadedCoroutine(2, 300L) }
    }
    println("main ends on ${Thread.currentThread().name}")
}

suspend fun multiThreadedCoroutine(number: Int, delay: Long){
    withContext(Dispatchers.Default){
        println("Routine $number starts work on ${Thread.currentThread().name}")
        delay(delay)
        println("Routine $number has finished on ${Thread.currentThread().name}")
    }
}
