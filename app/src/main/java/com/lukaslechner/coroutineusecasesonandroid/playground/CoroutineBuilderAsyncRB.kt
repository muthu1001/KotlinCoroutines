package com.lukaslechner.coroutineusecasesonandroid.playground

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    launchWithAsync()
}

fun launchWithAsync(){
    println("main starts")
    runBlocking {
        println("runBlocking starts")
       val deffered = async {
           println("async starts")
           mockNetworkRequest()
       }

        deffered.join()
        println("runBlocking ends")
    }
    println("main ends")
}

suspend fun mockNetworkRequest(): String{
    delay(1500)
    return "Data retrieved successfully"
}