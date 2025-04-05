package com.lukaslechner.coroutineusecasesonandroid.playground.flow.basics

import com.lukaslechner.coroutineusecasesonandroid.playground.utils.printWithTimePassed
import java.math.BigInteger

fun main(){
    val startTime = System.currentTimeMillis()
    calculateFactorial3Of(5)
        .forEach {
            printWithTimePassed(it, startTime)
        }
}

fun calculateFactorial3Of(i: Int): Sequence<BigInteger> = sequence {
    var factorial = BigInteger.ONE
    for (j in 1..i){
        Thread.sleep(10)
        factorial = factorial.multiply(BigInteger.valueOf(j.toLong()))
        yield(factorial)
    }
}
