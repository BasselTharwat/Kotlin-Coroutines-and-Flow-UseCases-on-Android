package com.lukaslechner.coroutineusecasesonandroid.playground.structuredconcurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.jar.Attributes.Name

fun main(){
    val scope = CoroutineScope(
        context = Job()
    )
    scope.launch {
        doSomeTasks()
        launch {
            println("Starting task 3")
            delay(300)
            println("Ending task 3")
        }
    }
    Thread.sleep(1000)
}

fun CoroutineScope.doSomeTasks(){

        launch {
            println("Starting task 1")
            delay(100)
            println("Ending task 1")
        }
        launch {
            println("Starting task 2")
            delay(200)
            println("Ending task 2")
        }


}