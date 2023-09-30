package com.lukaslechner.coroutineusecasesonandroid.fundamentals

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        println("this is start of main")
        joinAll (
            async {  basicCoroutineThreadInfo(1, 500)},
            async {  basicCoroutineThreadInfo(2, 300)},
            async {  basicCoroutineThreadInfo(3, 400)}
        )
        println("this is end of main")
    }
}

suspend fun basicCoroutineThreadInfo(number: Int, delay: Long){
    println("this is start of routine basic thread $number")
    println("basic coroutine thread number $number info ${Thread.currentThread().name}")
    delay(delay)
    println("this is end of routine basic thread $number")

}