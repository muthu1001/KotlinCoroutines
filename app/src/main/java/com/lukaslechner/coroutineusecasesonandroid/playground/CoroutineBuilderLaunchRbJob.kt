package com.lukaslechner.coroutineusecasesonandroid.playground

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    launchWithJob()
}

fun launchWithJob(){
    println("main starts")
    runBlocking {
        println("runBlocking starts")
       val job = launch {
           println("launch starts")
           mockNetworkRequest3()
           println("launch ends")

       }
        job.join()
        println("runBlocking ends")
    }
    println("main ends")
}


suspend fun mockNetworkRequest3(): String{
    delay(1500)
    return "Data retrieved successfully"
}