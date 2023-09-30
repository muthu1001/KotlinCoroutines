package com.lukaslechner.coroutineusecasesonandroid.playground

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    launchWithAsyncAwait()
}

fun launchWithAsyncAwait(){
    println("main starts")
    runBlocking {
        println("runBlocking starts")
       val deffered = async {
           println("async starts")
           mockNetworkRequest4()
       }

        val result = deffered.await()
        println(result)
        println("runBlocking ends")
    }
    println("main ends")
}

suspend fun mockNetworkRequest4(): String{
    delay(1500)
    return "Data retrieved successfully"
}