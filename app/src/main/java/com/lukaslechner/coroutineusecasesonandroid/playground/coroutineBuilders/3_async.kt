package com.lukaslechner.coroutineusecasesonandroid.playground.coroutineBuilders

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {

        val startTime = System.currentTimeMillis()

        val deferred1 = async {
            val result = networkCall(1)
            println("Result received: $result after ${elapsedMillis(startTime)}")
            result
        }

        val deferred2 = async {
            val result = networkCall(2)
            println("Result received: $result after ${elapsedMillis(startTime)}")
            result
        }

        val resultList = listOf( deferred1.await(), deferred2.await())
        println("Result list: $resultList after ${elapsedMillis(startTime)}")


    }
}


suspend fun networkCall(num: Int): String{
    delay(500)
    return "result $num"
}

fun elapsedMillis(startTime: Long) = System.currentTimeMillis() - startTime