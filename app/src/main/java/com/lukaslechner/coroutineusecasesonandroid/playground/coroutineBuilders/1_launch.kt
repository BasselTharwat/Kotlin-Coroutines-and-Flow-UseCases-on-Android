package com.lukaslechner.coroutineusecasesonandroid.playground.coroutineBuilders

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    println("main starts")
    runBlocking{
        delay(500L)
        println("printed from coroutine")
    }
    println("main ends")
}