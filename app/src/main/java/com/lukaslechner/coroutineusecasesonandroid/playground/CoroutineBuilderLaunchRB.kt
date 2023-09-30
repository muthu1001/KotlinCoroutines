package com.lukaslechner.coroutineusecasesonandroid.playground

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    launchWithRB()
}


fun launchWithRB(){
    println("main starts")
    runBlocking {
        println("runBlocking starts")
        launch {
            println("launch starts")
            mockNetworkRequest2()
            println("launch ends")
        }
        println("runBlocking ends")
    }
    println("main ends")
}

suspend fun mockNetworkRequest2(): String{
    delay(1500)
    return "Data retrieved successfully"
}