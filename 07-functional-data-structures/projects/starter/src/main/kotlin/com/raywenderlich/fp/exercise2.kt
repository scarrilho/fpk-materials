package com.raywenderlich.fp

fun <T> FList<T>.tail(): FList<T> = match(
    whenNil = { FList.empty() },
    whenCons = { _, tail -> tail }
)

fun main() {
    println(FList.empty<Int>().tail())
    println(FList.of(1).tail())
    println(FList.of(1, 2, 3).tail())
}