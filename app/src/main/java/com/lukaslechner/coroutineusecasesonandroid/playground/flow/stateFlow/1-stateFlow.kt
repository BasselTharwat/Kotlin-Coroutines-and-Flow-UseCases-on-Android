package com.lukaslechner.coroutineusecasesonandroid.playground.flow.stateFlow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

suspend fun main(){
    val counter = MutableStateFlow(0)

    coroutineScope {
        repeat(10000){
            launch {
                counter.update {
                    it + 1
                }
            }
        }
    }
    println(counter.value)
}