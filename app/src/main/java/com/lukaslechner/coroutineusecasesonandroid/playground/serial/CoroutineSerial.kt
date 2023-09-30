package com.lukaslechner.coroutineusecasesonandroid.playground.serial

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    val startTime = System.currentTimeMillis()
    //simpleLaunchSerial(startTime)
    simpleLaunchSequential(startTime)
}

fun simpleLaunchSequential(startTime: Long){
    runBlocking {
        println("runBlocking started at ${getDelay(startTime)}")
        val job1 = launch {
            mockNetworkRequest(1,startTime)
        }
        val job2 =launch {
            mockNetworkRequest(2,startTime)
        }
        val job3 = launch {
            mockNetworkRequest(3,startTime)
        }
        job1.join()
        job2.join()
        job3.join()
        println("runBlocking ended at ${getDelay(startTime)}")
    }
}

fun simpleLaunchSerial(startTime: Long){
    runBlocking {
        println("runBlocking started at ${getDelay(startTime)}")
        launch {
            mockNetworkRequest(1,startTime)
        }
        launch {
            mockNetworkRequest(2,startTime)
        }
        launch {
            mockNetworkRequest(3,startTime)
        }
        println("runBlocking ended at ${getDelay(startTime)}")
    }
}

suspend fun mockNetworkRequest(number: Int = 1,startTime: Long): String{
    println("Network $number started at ${getDelay(startTime)}")
    val actualDelay = 500 * (number%4)
    delay(actualDelay.toLong())
    println("Network $number ended at ${getDelay(startTime)}")
    return "Data retrieved successfully $number"
}

fun getDelay(startTime: Long):Long{
    val currentTime = System.currentTimeMillis()
    return currentTime - startTime
}