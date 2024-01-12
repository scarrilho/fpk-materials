package com.raywenderlich.fp.exercises

fun <A, B, C> ((A, B) -> C).flip(): (B, A) -> C =
    { b: B, a: A ->
        this(a, b)
    }

fun append(a: String, b: String): String = "$a $b"

fun main() {
    val flippedAppend = ::append.flip()
    println(append("First", "Second"))
    println(flippedAppend("First", "Second"))
}