package com.lukaslechner.coroutineusecasesonandroid.playground

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    println("main starts")
    runBlocking {
        delay(1000)
        println("runBlocking ends")
    }
    println("main ends")
}