package com.raywenderlich.fp.exercises

val half: (Int) -> Double = { a: Int -> a / 2.0 }
fun half(x: Int) = x / 2
fun half(x: Double) = x / 2

fun main() {
    println("half of 1 is ${half(1)}")
}