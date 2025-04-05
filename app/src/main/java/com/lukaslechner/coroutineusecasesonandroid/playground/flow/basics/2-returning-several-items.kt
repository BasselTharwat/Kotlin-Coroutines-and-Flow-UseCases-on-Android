package com.lukaslechner.coroutineusecasesonandroid.playground.flow.basics

import com.lukaslechner.coroutineusecasesonandroid.playground.utils.printWithTimePassed
import java.math.BigInteger

fun main(){
    val startTime = System.currentTimeMillis()
    calculateFactorial2Of(5)
        .forEach {
            printWithTimePassed(it, startTime)
        }
}

fun calculateFactorial2Of(i: Int): List<BigInteger> = buildList {
    var factorial = BigInteger.ONE
    for (j in 1..i){
        Thread.sleep(10)
        factorial = factorial.multiply(BigInteger.valueOf(j.toLong()))
        add(factorial)
    }
}
