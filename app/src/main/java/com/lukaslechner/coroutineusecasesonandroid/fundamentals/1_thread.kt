package com.lukaslechner.coroutineusecasesonandroid.fundamentals

import kotlin.concurrent.thread

fun main(){
    println("this is start of main")
    basicThread(1,500)
    basicThread(2,300)
    basicThread(3,400)
    println("this is end of main")
}

fun basicThread(number: Int,delay: Long){
    println("this is start of routine basic thread $number")
    thread {
        Thread.sleep(delay)
        println("this is end of routine basic thread $number")
    }
}