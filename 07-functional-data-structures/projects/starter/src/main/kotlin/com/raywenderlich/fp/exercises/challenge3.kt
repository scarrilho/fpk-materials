package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.FList
import com.raywenderlich.fp.head
import com.raywenderlich.fp.match

fun <T> FList<T>.get(index: Int): T =
    skip(index).head() ?: throw ArrayIndexOutOfBoundsException()

fun main() {
    val list = FList.of(1, 2, 3)
    println(list.get(0))
    println(list.get(1))
    println(list.get(2))
    println(list.get(3))
}