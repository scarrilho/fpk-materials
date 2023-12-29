package com.raywenderlich.fp.exercise1

import com.raywenderlich.fp.isValidNumber

fun sumInRange(input: List<String>, range: IntRange): Int =
    input
    .filter(::isValidNumber)
    .map(String::toInt)
    .filter { it in range }
    .sum()

fun main() {
    println(sumInRange(listOf("1", "10", "a", "7", "ad2", "3"), 0..5))
}