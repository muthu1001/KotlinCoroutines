package com.lukaslechner.coroutineusecasesonandroid.fundamentals

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        // println("this is start of main")
        for (i in 1 .. 1000000){
            launch {
                basicCoroutineVast(i, 50)
            }
        }
       // println("this is end of main")
    }
}

suspend fun basicCoroutineVast(number: Int, delay: Long){
    delay(delay)
    print(".")

}