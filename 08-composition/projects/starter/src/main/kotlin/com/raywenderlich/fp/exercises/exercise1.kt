package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.curry

fun <A, B, C> ((A) -> (B) -> C).uncurry(): (A, B) -> C =
    { a: A, b: B -> this(a)(b) }

fun main() {
    val sum = { a: Int, b: Int -> a + b }
    println(sum(2, 3))
    val sum2 = sum.curry().uncurry()
    println(sum2(2, 3))
}