package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals
import kotlin.concurrent.thread

fun main(){
    println("main starts on ${Thread.currentThread().name}")

    threadRoutine(1, 1000L)
    threadRoutine(2, 300L)

    println("main ends on ${Thread.currentThread().name}")
}

fun threadRoutine(number: Int, delay: Long){
    thread {
        println("Routine $number starts work on ${Thread.currentThread().name}")
        Thread.sleep(delay)
        println("Routine $number has finished on ${Thread.currentThread().name}")
    }
}
