package com.lukaslechner.coroutineusecasesonandroid.fundamentals

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        println("this is start of main")
        joinAll (
            async {  basicCoroutine(1, 500)},
            async {  basicCoroutine(2, 300)},
            async {  basicCoroutine(3, 400)}
        )
        println("this is end of main")
    }
}

suspend fun basicCoroutine(number: Int, delay: Long){
    println("this is start of routine basic thread $number")
    delay(delay)
    println("this is end of routine basic thread $number")

}