package com.lukaslechner.coroutineusecasesonandroid.playground

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SystemUnderTest{

    suspend fun functionWithDelay(): Int{
        delay(1000)
        return 42
    }
}

class TestClass() {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `functionWithDelay() should return 42`() = runTest {

        val realTimeStart = System.currentTimeMillis()
        val virtualTimeStart = currentTime

        val sut = SystemUnderTest()
        val result = sut.functionWithDelay()
        assert(result == 42)

        val realTimeDuration = System.currentTimeMillis() - realTimeStart
        val virtualTimeDuration = currentTime - virtualTimeStart

        println("realTimeDuration: $realTimeDuration")
        println("virtualTimeDuration: $virtualTimeDuration")

    }

}