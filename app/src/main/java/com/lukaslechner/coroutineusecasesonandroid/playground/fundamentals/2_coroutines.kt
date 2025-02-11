package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    println("main starts on ${Thread.currentThread().name}")
    runBlocking {
        launch {  coroutine(1, 500L) }
        launch { coroutine(2, 300L) }
        repeat(5){
            launch {
                println("Other task from ${Thread.currentThread().name}")
                delay(100L)
            }
        }
    }

    println("main ends on ${Thread.currentThread().name}")
}

suspend fun coroutine(number: Int, delay: Long){
    println("Routine $number starts work on ${Thread.currentThread().name}")
    delay(delay)
    println("Routine $number has finished on ${Thread.currentThread().name}")

}
