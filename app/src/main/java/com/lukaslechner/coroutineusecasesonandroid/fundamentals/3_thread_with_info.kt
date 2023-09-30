package com.lukaslechner.coroutineusecasesonandroid.fundamentals

import kotlin.concurrent.thread

fun main(){
    println("this is start of main")
    basicThreadWithInfo(1,500)
    basicThreadWithInfo(2,300)
    basicThreadWithInfo(3,400)
    println("this is end of main")
}

fun basicThreadWithInfo(number: Int,delay: Long){
    println("this is start of routine basic thread $number")
    thread {
        println("basic thread number $number info ${Thread.currentThread().name}")
        Thread.sleep(delay)
        println("this is end of routine basic thread $number")
    }
}