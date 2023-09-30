package com.lukaslechner.coroutineusecasesonandroid.fundamentals

import kotlin.concurrent.thread

fun main(){
    for (i in 1..1000000) {
        basicManyThread(i, 50)
    }

}

fun basicManyThread(number: Int,delay: Long){
    thread {
        Thread.sleep(delay)
        print(".")
    }
}