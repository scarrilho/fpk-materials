package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.*
import com.raywenderlich.fp.FCons

fun <T> FList<T>.takeLast(n: Int): FList<T> = match(
    whenNil = { FList.empty() },
    whenCons = { head, tail ->
        if (tail.size() >= n) tail.takeLast(n)
        else FCons(head, tail)
    }
)

fun main() {
    FList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .takeLast(3)
        .forEach(::println)
}