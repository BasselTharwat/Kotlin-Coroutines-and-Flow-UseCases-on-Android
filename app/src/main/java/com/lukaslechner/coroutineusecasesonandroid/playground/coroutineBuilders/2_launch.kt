package com.lukaslechner.coroutineusecasesonandroid.playground.coroutineBuilders

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        val job = launch(start = CoroutineStart.LAZY) {
            networkRequest()
            println("result received")
        }
        delay(200)
        job.start()
        job.join()
        println("end of runBlocking")
    }
}

suspend fun networkRequest(): String{
    delay(500L)
    return "Result"
}