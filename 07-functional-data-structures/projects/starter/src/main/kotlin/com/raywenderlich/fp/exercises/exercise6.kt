package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.*
import com.raywenderlich.fp.FCons

fun <T> FList<T>.take(num: Int): FList<T> = match(
    whenNil = { FList.empty() },
    whenCons = { head, tail ->
        if (num > 0) FCons(head, tail.take(num - 1))
        else FList.empty()
    }
)

fun main() {
    FList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 20)
        .take(3)
        .forEach(::println)
    FList.empty<Int>()
        .take(3)
        .forEach(::println)
}