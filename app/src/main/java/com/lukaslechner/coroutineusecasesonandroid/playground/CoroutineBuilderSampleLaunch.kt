package com.lukaslechner.coroutineusecasesonandroid.playground

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main(){
    println("main starts")
    GlobalScope.launch {
        delay(1000)
        println("GlobalScope ends")
    }
    //Thread.sleep(1200)
    println("main ends")
}