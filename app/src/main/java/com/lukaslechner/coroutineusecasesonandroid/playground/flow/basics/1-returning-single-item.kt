package com.lukaslechner.coroutineusecasesonandroid.playground.flow.basics

import java.math.BigInteger

fun main(){
    val result = calculateFactorialOf(5)
    println("Result: $result")
}

fun calculateFactorialOf(i: Int): BigInteger {
    var factorial = BigInteger.ONE
    for (j in 1..i){
        Thread.sleep(10)
        factorial = factorial.multiply(BigInteger.valueOf(j.toLong()))
    }
    return factorial
}
