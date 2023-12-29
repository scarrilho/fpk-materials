package com.raywenderlich.fp.exercise1

import com.raywenderlich.fp.times
import kotlin.system.measureTimeMillis

fun chrono(fn: () -> Unit): Long =
    measureTimeMillis(fn)

fun main() {
    val waitOneSec = { Thread.sleep(1000) }
    println(chrono { waitOneSec })
}