package com.lukaslechner.coroutineusecasesonandroid.playground.flow.hotncold

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

fun main(){
    val sharedFlow = MutableSharedFlow<Int>()

    val scope = CoroutineScope(Dispatchers.Default)

    scope.launch {
        repeat(5){
            println("Emitting $it")
            sharedFlow.emit(it)
            delay(200)
        }
    }

    scope.launch {
        sharedFlow.collect{
            println("Collected from 1: $it")
        }
    }

    scope.launch {
        sharedFlow.collect{
            println("Collected from 2: $it")
        }
    }

    Thread.sleep(2000)
}