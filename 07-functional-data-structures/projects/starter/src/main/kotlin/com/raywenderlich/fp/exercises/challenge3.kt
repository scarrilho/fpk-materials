package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.FList
import com.raywenderlich.fp.head

operator fun <T> FList<T>.get(index: Int): T =
    skip(index).head() ?: throw ArrayIndexOutOfBoundsException()

fun main() {
    val list = FList.of(1, 2, 3)
    println(list[0])
    println(list[1])
    println(list[2])
    println(list[3])
}