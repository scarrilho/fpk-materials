package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.*

fun <T> FList<T>.takeLast(n: Int): FList<T> = match(
    whenNil = { FList.empty() },
    whenCons = { head, tail ->
        if (tail.size() >= n) {
            tail.takeLast(n)
        } else {
            FCons(head, tail)
        }
    }
)

fun <T> FList<T>.skip(n: Int): FList<T> = match(
    whenNil = { FList.empty() },
    whenCons = { head, tail ->
        if (n > 0) {
            tail.skip(n - 1 )
        } else {
            FCons(head, tail)
        }
    }
)

fun <T> FList<T>.takeLast2(n: Int): FList<T> =
    skip(size() - n)

fun main() {
    /*FList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .takeLast(3)
        .forEach(::println)*/

    (0..6).forEach {
        println("takeLast $it")
        FList.of(1, 2, 3, 4, 5)
            .takeLast(it)
            .forEach(::println)
        println()
    }

    (0..6).forEach {
        println("Skipping $it")
        FList.of(1, 2, 3, 4, 5, 6)
            .skip(it)
            .forEach(::println)
    }

    (0..6).forEach {
        println("takeLast $it")
        FList.of(1, 2, 3, 4, 5)
            .takeLast2(it)
            .forEach(::println)
        println()
    }
}