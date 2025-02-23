package com.lukaslechner.coroutineusecasesonandroid.playground.structuredconcurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.sql.Time

fun main(){
    val scopeJob = Job()
    val coroutineScope = CoroutineScope(context = Dispatchers.Default + scopeJob)

    var childCoroutineJob : Job? = null
    val coroutineJob = coroutineScope.launch {

        childCoroutineJob = launch {

            println("Starting child coroutine")
            delay(200)
        }
        println("Starting coroutine")
        delay(100)

    }


    println("Is coroutineJob a child of scopeJob => ${scopeJob.children.contains(coroutineJob)}")
    println("Is childCoroutineJob a child of coroutineJob => ${coroutineJob.children.contains(childCoroutineJob)}")
}