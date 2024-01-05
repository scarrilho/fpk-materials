package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.FList
import com.raywenderlich.fp.match

fun <T> FList<T>.tail(): FList<T> = match(
    whenNil = { FList.empty() },
    whenCons = { _, tail -> tail }
)

fun main() {
    println(FList.empty<Int>().tail())
    println(FList.of(1).tail())
    println(FList.of(1, 2, 3).tail())
}