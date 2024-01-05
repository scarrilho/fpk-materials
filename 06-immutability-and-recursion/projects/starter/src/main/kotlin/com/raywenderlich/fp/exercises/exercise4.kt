package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.noTailRecFactorial
import com.raywenderlich.fp.recursiveFactorial
import com.raywenderlich.fp.tailRecFactorial
import java.sql.Time

fun chrono(times: Int = 1, fn: () -> Unit): Long {
    val start = System.nanoTime()
    (1..times).forEach( { fn() })
    return System.nanoTime() - start
}

fun main() {
    val times = 1000000
    println(chrono(times) { recursiveFactorial(50) })
    println(chrono(times) { tailRecFactorial(50) })
    println(chrono(times) { noTailRecFactorial(50) })
}