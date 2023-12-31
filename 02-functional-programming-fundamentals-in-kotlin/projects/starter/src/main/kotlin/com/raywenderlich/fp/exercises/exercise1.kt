package com.raywenderlich.fp.exercises

fun isEven(num: Int): Boolean = num % 2 == 0

val eventInSet: (Int) -> Boolean = { a: Int -> a % 2 == 0 }

fun main() {
    val nums = listOf(1, 2, 3)

    nums.forEach { println(isEven(it)) }
    println("O is even? ${eventInSet(0)}")
}