package com.raywenderlich.fp.challenges

import java.util.function.Predicate

fun even(x: Int): Boolean = (x % 2 == 0)

val evenInSet: (Int) -> Boolean = { a: Int -> a % 2 == 0}

fun main() {
    (Int.MIN_VALUE..Int.MAX_VALUE)
        .filter(evenInSet)
        .forEach{ println(it) }
}