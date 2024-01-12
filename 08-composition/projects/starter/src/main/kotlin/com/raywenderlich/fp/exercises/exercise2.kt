package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.curry
import java.lang.Thread.sleep

fun <A, B, C> ((A, B) -> C).flip(): (B, A) -> C =
    { b: B, a: A ->
        this(a, b)
    }

fun append(a: String, b: String): String = "$a $b"

fun runDelayed(fn: () -> Unit, delay: Long) {
    sleep(delay)
    fn()
}

fun main() {
    val flippedAppend = ::append.flip()
    println(append("First", "Second"))
    println(flippedAppend("First", "Second"))

    runDelayed({
        println("Delayed")
    }, 1000)

    val runDelayed1Second = ::runDelayed.flip()
        .curry()
        .invoke(1000L)

    runDelayed1Second {
        println("Delayed")
    }
}