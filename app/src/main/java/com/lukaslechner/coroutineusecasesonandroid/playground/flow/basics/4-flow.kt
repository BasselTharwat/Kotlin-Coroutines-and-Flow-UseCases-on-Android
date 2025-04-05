package com.lukaslechner.coroutineusecasesonandroid.playground.flow.basics

import com.lukaslechner.coroutineusecasesonandroid.playground.utils.printWithTimePassed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.math.BigInteger

fun main()  {
    runBlocking {
    val startTime = System.currentTimeMillis()

        launch {
            calculateFactorial4Of(5)
                .collect() {
                    printWithTimePassed(it, startTime)
                }
        }
        println("Give me more work!")
    }
}

fun calculateFactorial4Of(i: Int): Flow<BigInteger> = flow {
    var factorial = BigInteger.ONE
    for (j in 1..i){
        delay(10)
        factorial = factorial.multiply(BigInteger.valueOf(j.toLong()))
        emit(factorial)
    }
}.flowOn(Dispatchers.Default)
