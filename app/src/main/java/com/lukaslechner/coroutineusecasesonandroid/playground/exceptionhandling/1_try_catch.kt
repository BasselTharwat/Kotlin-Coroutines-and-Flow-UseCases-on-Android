package com.lukaslechner.coroutineusecasesonandroid.playground.exceptionhandling

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun main(){
    val scope = CoroutineScope(Job())

    scope.launch {
        try {
            functionThatThrows()
        }catch (e: Exception){
            println("exception caught: $e")
        }
    }


}

fun functionThatThrows(){
    throw RuntimeException()
}